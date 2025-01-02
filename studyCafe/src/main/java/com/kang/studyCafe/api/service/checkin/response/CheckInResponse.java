package com.kang.studyCafe.api.service.checkin.response;

import com.kang.studyCafe.api.service.desk.response.DeskResponse;
import com.kang.studyCafe.api.service.user.response.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CheckInResponse {
    private Long id;

    private DeskResponse deskResponse;

    private UserResponse userResponse;

    private LocalDateTime checkInTime;

    private LocalDateTime expectedCheckOutTime;

    @Builder
    public CheckInResponse(Long id, DeskResponse deskResponse, LocalDateTime checkInTime, LocalDateTime expectedCheckOutTime,UserResponse userResponse) {
        this.id = id;
        this.deskResponse = deskResponse;
        this.userResponse = userResponse;
        this.checkInTime = checkInTime;
        this.expectedCheckOutTime = expectedCheckOutTime;
    }
}
