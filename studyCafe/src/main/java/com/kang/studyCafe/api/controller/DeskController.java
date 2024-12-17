package com.kang.studyCafe.api.controller;

import com.kang.studyCafe.api.ApiResponse;
import com.kang.studyCafe.api.service.desk.DeskService;
import com.kang.studyCafe.api.service.desk.response.DeskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DeskController {
    private final DeskService deskService;

    @GetMapping("/api/vi/desks/display")
    public ApiResponse<List<DeskResponse>> getAvailableDesks() {
        return ApiResponse.ok(deskService.getAvailableDeskList());
    }



}
