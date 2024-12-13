package com.kang.studyCafe.api.service.desk;

import com.kang.studyCafe.api.domain.desk.Desk;
import com.kang.studyCafe.api.domain.desk.DeskStatus;
import com.kang.studyCafe.api.domain.desk.DeskType;
import com.kang.studyCafe.api.service.desk.response.DeskResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kang.studyCafe.api.domain.desk.DeskStatus.*;
import static com.kang.studyCafe.api.domain.desk.DeskType.*;

@Service
@Transactional(readOnly = true)
public class DeskService {

    //고객이 예약 가능한 Desk 목록을 조회한다.
    public List<DeskResponse> getAvailableDeskList() {

        return List.of(DeskResponse.builder()
                .deskStatus(AVAILABLE)
                .deskNumber(2)
                .deskType(SINGLE)
                .build()
        );
    }
}
