package com.kang.studyCafe.api.service.desk;

import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.desk.DeskRepository;
import com.kang.studyCafe.api.service.desk.response.DeskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.kang.studyCafe.domain.desk.DeskStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeskService {

    private final DeskRepository deskRepository;

    //고객이 예약 가능한 Desk 목록을 조회한다.
    public List<DeskResponse> getAvailableDeskList() {

        List<Desk> deskList = deskRepository.findAllByDeskStatusIn(getCustomerViewStates());

        return deskList.stream().map(DeskResponse::of)
                .collect(Collectors.toList());
    }
}
