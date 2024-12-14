package com.kang.studyCafe.api.service.desk;

import com.kang.studyCafe.api.IntegrationTestSupport;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.desk.DeskRepository;
import com.kang.studyCafe.domain.desk.DeskStatus;
import com.kang.studyCafe.domain.desk.DeskType;
import com.kang.studyCafe.api.service.desk.response.DeskResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.kang.studyCafe.domain.desk.DeskStatus.*;
import static com.kang.studyCafe.domain.desk.DeskType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class DeskServiceTest extends IntegrationTestSupport {

    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private DeskService deskService;

    @DisplayName("고객은 이용 가능한 Desk만 조회 가능하다.")
    @Test
    public void getAvailableDesk() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, IN_USE);
        Desk desk2 = createDesk(2,SINGLE, AVAILABLE);
        Desk desk3= createDesk(3,PRIVATE, UNAVAILABLE);
        deskRepository.saveAll(List.of(desk, desk2, desk3));

        //when
        List<DeskResponse> availableDesks = deskService.getAvailableDeskList();

        //then
        assertThat(availableDesks).hasSize(1)
                .extracting("deskNumber","deskType","deskStatus")
                .containsExactlyInAnyOrder(
                        tuple(2,SINGLE,AVAILABLE)
                );

    }

    private Desk createDesk(int deskNumber, DeskType deskType, DeskStatus deskStatus) {

        return Desk.builder()
                .deskType(deskType)
                .deskNumber(deskNumber)
                .deskStatus(deskStatus)
                .build();
    }
}