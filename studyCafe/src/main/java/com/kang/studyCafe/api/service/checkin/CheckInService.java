package com.kang.studyCafe.api.service.checkin;

import com.kang.studyCafe.api.controller.checkin.request.CheckInCreateRequest;
import com.kang.studyCafe.api.service.checkin.request.CheckInCreateServiceRequest;
import com.kang.studyCafe.api.service.checkin.response.CheckInResponse;
import com.kang.studyCafe.api.service.desk.response.DeskResponse;
import com.kang.studyCafe.api.service.message.MessageService;
import com.kang.studyCafe.api.service.user.response.UserResponse;
import com.kang.studyCafe.domain.checkin.CheckIn;
import com.kang.studyCafe.domain.checkin.CheckInRepository;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.desk.DeskRepository;
import com.kang.studyCafe.domain.desk.DeskStatus;
import com.kang.studyCafe.domain.ticket.Ticket;
import com.kang.studyCafe.domain.ticket.TicketStatus;
import com.kang.studyCafe.domain.user.User;
import com.kang.studyCafe.domain.user.UserRepository;
import com.kang.studyCafe.domain.user.UserStatus;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckInService {

    private final DeskRepository deskRepository;

    private final UserRepository userRepository;

    private final CheckInRepository checkInRepository;

    private final MessageService messageService;

    @Transactional
    public CheckInResponse createCheckIn(CheckInCreateServiceRequest request, LocalDateTime checkInTime) {
        CheckIn savedCheckIn = null;
        // 이용가능한 desk 인지 조회 및 확인
        Desk desk = getValidatedDesk(request.getDeskId());

        // 회원 조회
        User user = getValidatedUserWithTicket(request.getUserId());

        // 체크인 생성
        CheckIn checkIn = CheckIn.createCheckIn(desk, user, checkInTime);
        if (checkIn == null) {
            throw new RuntimeException("입실 등록을 실패 했습니다.");
        }

        try {
            savedCheckIn = checkInRepository.save(checkIn);
        } catch (ConstraintViolationException e) {
            throw new RuntimeException("입실 등록을 실패 했습니다.");
        }

        // desk 상태 이용중으로 변경
        desk.updateDeskStatus(DeskStatus.IN_USE);
        // user 상태 체크인을 변경
        user.updateUserStatus(UserStatus.CHECK_IN);
        String content = user.getUserName() + "님 " + desk.getDeskNumber() + "번 " +
                "좌석에 입실하셨습니다";
        messageService.sendMessage("KangStudyCafe", user.getUserContactNum(), content);

        return CheckInResponse.builder()
                .id(savedCheckIn.getId())
                .checkInTime(savedCheckIn.getCheckInTime())
                .expectedCheckOutTime(savedCheckIn.getExpectedCheckOutTime())
                .deskResponse(DeskResponse.of(desk))
                .userResponse(UserResponse.of(user))
                .build();
    }

    private User getValidatedUserWithTicket(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));

        if (user.getUserStatus().equals(UserStatus.CHECK_IN)) {
            throw new IllegalArgumentException("이미 입실중인 회원 입니다.");
        }

        if (user.getTicket() == null) {
            throw new IllegalArgumentException("이용권이 없는 회원입니다.");
        }
        // 회원이 가진 티켓 검증
        Ticket ticket = user.getTicket();
        if (ticket.getTicketStatus().equals(TicketStatus.UNAVAILABLE)) {
            throw new IllegalArgumentException("사용 불가능한 이용권 입니다.");
        }

        return user;
    }

    private Desk getValidatedDesk(Long deskId) {
        Desk desk = deskRepository.findById(deskId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌석입니다."));
        if (desk.getDeskStatus().equals(DeskStatus.IN_USE)) {
            throw new IllegalArgumentException("이미 사용중인 좌석 입니다.");
        }
        if (desk.getDeskStatus().equals(DeskStatus.UNAVAILABLE)) {
            throw new IllegalArgumentException("사용 불가 좌석 입니다.");
        }

        return desk;
    }
}
