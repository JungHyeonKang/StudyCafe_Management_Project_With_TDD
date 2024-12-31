package com.kang.studyCafe.domain.ticket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TicketStatus {
    AVAILABLE("사용가능"),
    UNAVAILABLE("사용 불가능");

    private final String text;
}
