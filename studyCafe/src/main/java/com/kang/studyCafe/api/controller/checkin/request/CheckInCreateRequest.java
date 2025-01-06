package com.kang.studyCafe.api.controller.checkin.request;

import com.kang.studyCafe.api.service.checkin.request.CheckInCreateServiceRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CheckInCreateRequest {

    @NotNull(message = "회원 정보는 필수 값 입니다.")
    private Long userId;

    @NotNull(message = "좌석 정보는 필수 값 입니다.")
    private Long deskId;

    public CheckInCreateServiceRequest toServiceRequest() {
        return CheckInCreateServiceRequest.builder()
                .deskId(deskId)
                .userId(userId)
                .build();
    }

}
