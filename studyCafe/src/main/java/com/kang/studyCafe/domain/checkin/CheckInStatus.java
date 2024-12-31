package com.kang.studyCafe.domain.checkin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CheckInStatus {

    INIT("체크인 생성"),
    CANCELED("체크인 취소"),
    PAYMENT_COMPLETED("결제완료"),
    PAYMENT_FAILED("결제실패"),
    RECEIVED("체크인 접수"),
    COMPLETED("체크인 처리완료");

    private final String text;
}
