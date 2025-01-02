package com.kang.studyCafe.api.service.user.response;


import com.kang.studyCafe.domain.ticket.Ticket;
import com.kang.studyCafe.domain.user.User;
import com.kang.studyCafe.domain.user.UserStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String userName;

    private UserStatus userStatus;

    private Ticket ticket;

    @Builder
    private UserResponse(Long id, String userName, UserStatus userStatus, Ticket ticket) {
        this.id = id;
        this.userName = userName;
        this.userStatus = userStatus;
        this.ticket = ticket;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .userStatus(user.getUserStatus())
                .ticket(user.getTicket())
                .build();
    }
}
