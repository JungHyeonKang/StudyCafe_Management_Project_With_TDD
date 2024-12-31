package com.kang.studyCafe.api.controller.checkin.request;

import jakarta.validation.constraints.NotNull;

public class CheckInCreateRequest {

    @NotNull(message = "회원 정보는 필수 값 입니다.")
    private Long userId;

    @NotNull(message = "좌석 정보는 필수 값 입니다.")
    private Long deskId;

}
