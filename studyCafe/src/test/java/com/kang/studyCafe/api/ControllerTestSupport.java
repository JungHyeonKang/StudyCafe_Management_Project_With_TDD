package com.kang.studyCafe.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.studyCafe.api.controller.desk.DeskController;
import com.kang.studyCafe.api.service.desk.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = DeskController.class)
@ActiveProfiles("test")
public class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 직렬화/역직렬화

    // 컨테이너에 deskService mock 객체를 넣어준다 -- deskController는 deskService 의존성 주입 받기 때문에
    // 해당 MockTest를 띄우기 위해 deskService MockitoBean 주입해주는 어노테이션
    @MockitoBean
    protected DeskService deskService;
}
