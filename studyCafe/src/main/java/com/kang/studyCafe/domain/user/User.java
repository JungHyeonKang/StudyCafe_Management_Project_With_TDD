package com.kang.studyCafe.domain.user;

import com.kang.studyCafe.domain.BaseEntity;
import com.kang.studyCafe.domain.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToOne
    private Ticket ticket;

    @Builder
    private User(String userName, UserStatus userStatus, Ticket ticket) {
        this.userName = userName;
        this.userStatus = userStatus;
        this.ticket = ticket;
    }

    public static User createUser(String userName, UserStatus userStatus, Ticket ticket) {
        return User.builder()
                .userStatus(userStatus)
                .ticket(ticket)
                .userName(userName)
                .build();
    }

    public void updateUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
