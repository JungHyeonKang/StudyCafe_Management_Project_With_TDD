package com.kang.studyCafe.domain.checkin;

import com.kang.studyCafe.domain.BaseEntity;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String checkInTime;

    private String checkOutTime;


}
