package com.kang.studyCafe.api.service.message;

import com.kang.studyCafe.api.client.message.MessageClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageClient messageClient;

    @InjectMocks // 필요한 Mock 객체 자동주입
    private MessageService messageService;

    @DisplayName("문자 전송 테스트")
    @Test
    public void sendMessage() throws Exception {

        // given
        BDDMockito.given(messageClient.sendMessage(anyString(), anyString(), anyString()))
                .willReturn(true);

        // when
        boolean result = messageService.sendMessage("sender", "receiver", "message");

        // then
        BDDMockito.verify(messageClient).sendMessage(anyString(), anyString(), anyString());

        assertThat(result).isTrue();

    }


}