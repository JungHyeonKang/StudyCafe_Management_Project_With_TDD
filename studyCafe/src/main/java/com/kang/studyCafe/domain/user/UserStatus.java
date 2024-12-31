package com.kang.studyCafe.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    CHECK_IN("입실"),
    CHECK_OUT("미입실");

    private final String text;
}
