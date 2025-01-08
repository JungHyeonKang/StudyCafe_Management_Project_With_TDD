package com.kang.studyCafe.api.client.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageClient {
    /**
     * MessageClient 는 외부 서비스를 사용하고 있다고 가정하고  Mock 테스트를 위한 기능이므로 로그로 대체
     */
    public boolean sendMessage(String from, String to, String content) {
        StringBuilder sb = new StringBuilder();
        sb.append("[메시지 전송]").append(System.lineSeparator());
        sb.append("보내는 번호 : ").append(from).append(System.lineSeparator());
        sb.append("수신자 번호 : ").append(to).append(System.lineSeparator());
        sb.append("내용 : ").append(content).append(System.lineSeparator());
        sb.toString();
        log.info("메시지 전송 {} ", sb);
        return true;
    }
}
