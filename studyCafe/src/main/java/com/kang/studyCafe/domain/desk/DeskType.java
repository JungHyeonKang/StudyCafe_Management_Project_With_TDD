package com.kang.studyCafe.domain.desk;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeskType {
    SINGLE("싱글 데스크"),
    LAPTOP("노트북 데스크"),
    CAFE("카페형 데스크"),
    PRIVATE("프라이빗 데스크");

    private final String text;

}
