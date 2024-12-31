package com.kang.studyCafe.api.service.checkin;

import com.kang.studyCafe.api.controller.checkin.request.CheckInCreateRequest;
import com.kang.studyCafe.api.service.checkin.request.CheckInCreateServiceRequest;
import com.kang.studyCafe.api.service.checkin.response.CheckInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckInService {

    @Transactional
    public CheckInResponse createCheckIn(CheckInCreateServiceRequest request, LocalDateTime checkInTime) {

        /**
         * 1. desk가 이용가능한 desk 인지 조회 및 확인
         * 2. user가 존재하고 좌석 입실 가능한지 확인
         * 3. checkin 생성 (checkInTime (현재시간) ,checkOutTime(종료 시간) 구함 )
         * 4. desk 상태 이용중 변경
         * 5. user 상태 변경
         */

        return null;
    }
}
