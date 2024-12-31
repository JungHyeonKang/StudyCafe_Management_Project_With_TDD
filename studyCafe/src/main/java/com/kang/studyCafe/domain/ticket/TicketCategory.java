package com.kang.studyCafe.domain.ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum TicketCategory {

    TIME_TICKET("시간권","시간 충전 후 사용한 시간만큼 차감하여 이용가능"),
    SINGLE_DAY_TICKET("당일권","1회권으로 충전한 시간만큼 이용가능 (퇴실시 이용권 종료)");

    private final String text;
    private final String description;

    public static boolean isDeductionTicket(TicketCategory ticketCategory) {
        return List.of(TIME_TICKET).contains(ticketCategory);
    }

}
