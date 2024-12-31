package com.kang.studyCafe.domain.ticket;

import com.kang.studyCafe.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    private int remainingTime;

    @Builder
    private Ticket(TicketType ticketType, TicketStatus ticketStatus, int remainingTime) {
        this.ticketType = ticketType;
        this.ticketStatus = ticketStatus;
        this.remainingTime = remainingTime;
    }

    public static Ticket createTicket(TicketStatus ticketStatus, TicketType ticketType) {
        return Ticket.builder()
                .ticketStatus(ticketStatus)
                .ticketType(ticketType)
                .remainingTime(ticketType.getDurationInHours())
                .build();
    }

}
