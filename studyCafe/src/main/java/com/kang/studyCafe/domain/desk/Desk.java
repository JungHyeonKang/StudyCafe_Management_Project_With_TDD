package com.kang.studyCafe.domain.desk;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int deskNumber;
    @Enumerated(EnumType.STRING)
    private DeskStatus deskStatus;

    @Enumerated(EnumType.STRING)
    private DeskType deskType;

    @Builder
    public Desk(int deskNumber, DeskStatus deskStatus, DeskType deskType) {
        this.deskNumber = deskNumber;
        this.deskStatus = deskStatus;
        this.deskType = deskType;
    }
}
