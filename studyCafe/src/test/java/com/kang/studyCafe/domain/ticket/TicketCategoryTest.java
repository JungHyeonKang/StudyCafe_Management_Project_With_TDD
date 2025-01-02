package com.kang.studyCafe.domain.ticket;

import com.kang.studyCafe.api.IntegrationTestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TicketCategoryTest extends IntegrationTestSupport {

    @DisplayName("티켓 카테고리가 시간 차감 방식인지 체크한다.")
    @Test
    public void isDeductionTicket() throws Exception {

        //when
        boolean result = TicketCategory.isDeductionTicket(TicketCategory.TIME_TICKET);

        //then
        assertThat(result).isTrue();

    }

    @DisplayName("티켓 카테고리가 시간 차감 방식이 아니면 false를 리턴한다.")
    @Test
    public void isNotDeductionTicket() throws Exception {

        //when
        boolean result = TicketCategory.isDeductionTicket(TicketCategory.SINGLE_DAY_TICKET);

        //then
        assertThat(result).isFalse();

    }

}