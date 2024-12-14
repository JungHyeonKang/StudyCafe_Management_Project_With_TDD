package com.kang.studyCafe.api.domain.desk;

import com.kang.studyCafe.api.IntegrationTestSupport;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.desk.DeskRepository;
import com.kang.studyCafe.domain.desk.DeskStatus;
import com.kang.studyCafe.domain.desk.DeskType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.kang.studyCafe.domain.desk.DeskStatus.*;
import static com.kang.studyCafe.domain.desk.DeskType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class DeskRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private DeskRepository deskRepository;

    @DisplayName("예약가능한 자리를 조회한다.")
    @Test
    public void findAllByDeskStatusIn() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, IN_USE);
        Desk desk2 = createDesk(2,SINGLE, AVAILABLE);
        Desk desk3= createDesk(3,PRIVATE, UNAVAILABLE);
        deskRepository.saveAll(List.of(desk, desk2, desk3));

        //when
        List<Desk> desks = deskRepository.findAllByDeskStatusIn(getCustomerViewStates());

        //then
        assertThat(desks).hasSize(1)
                .extracting("deskType", "deskNumber", "deskStatus")
                .containsExactlyInAnyOrder(
                        tuple(SINGLE, 2, AVAILABLE)
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