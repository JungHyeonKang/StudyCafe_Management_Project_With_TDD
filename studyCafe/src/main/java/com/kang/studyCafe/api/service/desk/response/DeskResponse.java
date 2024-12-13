package com.kang.studyCafe.api.service.desk.response;

import com.kang.studyCafe.api.domain.desk.Desk;
import com.kang.studyCafe.api.domain.desk.DeskStatus;
import com.kang.studyCafe.api.domain.desk.DeskType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeskResponse {

    private int deskNumber;

    private DeskStatus deskStatus;

    private DeskType deskType;

    @Builder
    public DeskResponse(int deskNumber, DeskStatus deskStatus, DeskType deskType) {
        this.deskNumber = deskNumber;
        this.deskStatus = deskStatus;
        this.deskType = deskType;
    }

    public static DeskResponse of(Desk desk) {
        return DeskResponse.builder()
                .deskNumber(desk.getDeskNumber())
                .deskStatus(desk.getDeskStatus())
                .deskType(desk.getDeskType())
                .build();
    }
}
