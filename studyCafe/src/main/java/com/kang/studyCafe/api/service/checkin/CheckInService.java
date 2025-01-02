package com.kang.studyCafe.api.service.checkin;

import com.kang.studyCafe.api.controller.checkin.request.CheckInCreateRequest;
import com.kang.studyCafe.api.service.checkin.request.CheckInCreateServiceRequest;
import com.kang.studyCafe.api.service.checkin.response.CheckInResponse;
import com.kang.studyCafe.api.service.desk.response.DeskResponse;
import com.kang.studyCafe.api.service.user.response.UserResponse;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.desk.DeskRepository;
import com.kang.studyCafe.domain.desk.DeskStatus;
import com.kang.studyCafe.domain.user.User;
import com.kang.studyCafe.domain.user.UserRepository;
import com.kang.studyCafe.domain.user.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckInService {

    private final DeskRepository deskRepository;

    private final UserRepository userRepository;

    @Transactional
    public CheckInResponse createCheckIn(CheckInCreateServiceRequest request, LocalDateTime checkInTime) {

        /**
         * 1. desk가 이용가능한 desk 인지 조회 및 확인
         * 2. user가 존재하고 좌석 입실 가능한지 확인
         * 3. checkin 생성 (checkInTime (현재시간) ,checkOutTime(종료 시간) 구함 )
         * 4. desk 상태 이용중 변경
         * 5. user 상태 변경
         */
        Desk desk = deskRepository.findById(request.getDeskId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌석입니다."));
        if (desk.getDeskStatus().equals(DeskStatus.IN_USE)) {
            throw new IllegalArgumentException("이미 사용중인 좌석 입니다.");
        }
        if (desk.getDeskStatus().equals(DeskStatus.UNAVAILABLE)) {
            throw new IllegalArgumentException("사용 불가 좌석 입니다.");
        }
        desk.updateDeskStatus(DeskStatus.IN_USE);

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));

        if (user.getUserStatus().equals(UserStatus.CHECK_IN)) {
            throw new IllegalArgumentException("이미 입실중인 회원 입니다.");
        }
        user.updateUserStatus(UserStatus.CHECK_IN);

        return CheckInResponse.builder()
                .id(1L)
                .checkInTime(checkInTime)
                .expectedCheckOutTime(checkInTime.plusHours(50))
                .deskResponse(DeskResponse.of(desk))
                .userResponse(UserResponse.of(user))
                .build();
    }
}
