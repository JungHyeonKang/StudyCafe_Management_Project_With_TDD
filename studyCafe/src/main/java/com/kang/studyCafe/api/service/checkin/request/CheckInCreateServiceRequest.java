package com.kang.studyCafe.api.service.checkin.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckInCreateServiceRequest {

    private Long userId;
    private Long deskId;

    @Builder
    public CheckInCreateServiceRequest(Long userId, Long deskId) {
        this.userId = userId;
        this.deskId = deskId;
    }
}
