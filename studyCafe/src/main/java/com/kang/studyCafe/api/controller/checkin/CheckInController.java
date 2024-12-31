package com.kang.studyCafe.api.controller.checkin;

import com.kang.studyCafe.api.ApiResponse;
import com.kang.studyCafe.api.controller.checkin.request.CheckInCreateRequest;
import com.kang.studyCafe.api.service.checkin.CheckInService;
import com.kang.studyCafe.api.service.checkin.response.CheckInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping("/api/v1/checkIn/new")
    public ApiResponse<CheckInResponse> createCheckIn(@Validated @RequestBody CheckInCreateRequest checkInCreateRequest) {
        return ApiResponse.ok(checkInService.createCheckIn(checkInCreateRequest.toServiceRequest(),LocalDateTime.now()));
    }

}
