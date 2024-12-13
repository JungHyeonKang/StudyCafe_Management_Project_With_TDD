package com.kang.studyCafe.api.domain.desk;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum DeskStatus {

    AVAILABLE("사용 가능"),
    UNAVAILABLE("사용 불가"),
    IN_USE("이용중");

    private final String text;
    
    // 고객이 예약화면에서 볼 수 있는 상태값 리스트 조회
    public static List<DeskStatus> getCustomerViewStates(){
        return List.of(AVAILABLE);
    }


}
