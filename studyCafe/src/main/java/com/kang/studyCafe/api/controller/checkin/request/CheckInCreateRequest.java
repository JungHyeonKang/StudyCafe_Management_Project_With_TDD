package com.kang.studyCafe.api.controller.checkin.request;

import com.kang.studyCafe.api.service.checkin.request.CheckInCreateServiceRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 없으면 objectmapper 직렬화/역직렬화가 안되므로 추가 순서 : 대상 클래스의 기본 생성자로 객체 생성 -> getter, setter 를 보며 역직렬화 시도
public class CheckInCreateRequest {

    @NotNull(message = "회원 정보는 필수 값 입니다.")
    private Long userId;

    @NotNull(message = "좌석 정보는 필수 값 입니다.")
    private Long deskId;

    @Builder
    private CheckInCreateRequest(Long userId, Long deskId) {
        this.userId = userId;
        this.deskId = deskId;
    }

    public CheckInCreateServiceRequest toServiceRequest() {
        return CheckInCreateServiceRequest.builder()
                .deskId(deskId)
                .userId(userId)
                .build();
    }

}
