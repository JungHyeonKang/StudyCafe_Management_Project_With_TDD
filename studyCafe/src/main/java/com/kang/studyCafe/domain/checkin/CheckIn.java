package com.kang.studyCafe.domain.checkin;

import com.kang.studyCafe.domain.BaseEntity;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.ticket.TicketCategory;
import com.kang.studyCafe.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.kang.studyCafe.domain.ticket.TicketCategory.isDeductionTicket;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckIn extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private CheckInStatus checkInStatus;

    @OneToOne
    private Desk desk;

    @OneToOne
    private User user;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime; // 실제 체크아웃한 시간

    private LocalDateTime expectedCheckOutTime; // 체크아웃 예정 시간

    @Builder
    private CheckIn(CheckInStatus checkInStatus, Desk desk, User user, LocalDateTime checkInTime) {
        this.checkInStatus = checkInStatus;
        this.desk = desk;
        this.user = user;
        this.checkInTime = checkInTime;
        this.expectedCheckOutTime = calculateCheckOutTime(checkInTime);
    }

    public static CheckIn createCheckIn(Desk desk,User user,LocalDateTime checkInTime) {
        return CheckIn.builder()
                .checkInStatus(CheckInStatus.INIT)
                .desk(desk)
                .user(user)
                .checkInTime(checkInTime)
                .build();
    }

    private LocalDateTime calculateCheckOutTime(LocalDateTime checkInTime) {
        return checkInTime.plusHours(user.getTicket().getRemainingTime());
    }

}
