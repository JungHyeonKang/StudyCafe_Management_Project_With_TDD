package com.kang.studyCafe.api.service.checkin.response;

import com.kang.studyCafe.api.service.desk.response.DeskResponse;

public class CheckInResponse {
    private Long id;

    private DeskResponse deskResponse;

    private String checkInTime;

    private String checkOutTime;
}
