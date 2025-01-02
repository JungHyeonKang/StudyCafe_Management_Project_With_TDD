package com.kang.studyCafe.domain.desk;

import com.kang.studyCafe.api.IntegrationTestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeskTest extends IntegrationTestSupport {
    @DisplayName("주어진 DeskStatus 상태로 DeskStatus를 수정 할 수 있다.")
    @Test
    public void updateDeskStatus() throws Exception {
        //given
        Desk desk = Desk.builder()
                .deskNumber(1)
                .deskStatus(DeskStatus.AVAILABLE)
                .deskType(DeskType.LAPTOP)
                .build();

        //when
        desk.updateDeskStatus(DeskStatus.IN_USE);

        //then
        assertThat(desk.getDeskStatus()).isEqualTo(DeskStatus.IN_USE);

    }
}