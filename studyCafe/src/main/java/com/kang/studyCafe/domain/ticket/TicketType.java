package com.kang.studyCafe.domain.ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TicketType {

    TIME_TICKET_50_HOURS("시간권 50시간", 50,TicketCategory.TIME_TICKET),
    TIME_TICKET_100_HOURS("시간권 100시간", 100,TicketCategory.TIME_TICKET),
    TIME_TICKET_150_HOURS("시간권 150시간", 150,TicketCategory.TIME_TICKET),
    SINGLE_DAY_TICKET_2_HOURS("당일권 2시간", 2,TicketCategory.SINGLE_DAY_TICKET),
    SINGLE_DAY_TICKET_4_HOURS("당일권 4시간", 4,TicketCategory.SINGLE_DAY_TICKET),
    SINGLE_DAY_TICKET_6_HOURS("당일권 6시간", 6,TicketCategory.SINGLE_DAY_TICKET);

    private final String text;
    private final int durationInHours;
    private final TicketCategory ticketCategory;


}
