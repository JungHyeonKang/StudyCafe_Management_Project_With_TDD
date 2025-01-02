package com.kang.studyCafe.domain.user;

import com.kang.studyCafe.api.IntegrationTestSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest extends IntegrationTestSupport {

    @DisplayName("주어진 UserStatus 상태로 userStatus 수정 할 수 있다.")
    @Test
    public void updateUserStatusToCheckIn() throws Exception {
        //given
        User user = User.builder()
                .userName("홍길동")
                .userStatus(UserStatus.CHECK_OUT)
                .build();

        //when
        user.updateUserStatus(UserStatus.CHECK_IN);

        //then
        assertThat(user.getUserStatus()).isEqualTo(UserStatus.CHECK_IN);
    }

}