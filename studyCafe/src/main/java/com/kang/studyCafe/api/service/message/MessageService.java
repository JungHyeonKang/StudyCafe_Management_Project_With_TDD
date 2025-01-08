package com.kang.studyCafe.api.service.message;

import com.kang.studyCafe.api.client.message.MessageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageClient messageClient;

    public boolean sendMessage(String from, String to, String content) {

        return messageClient.sendMessage(from, to, content);
    }
}
