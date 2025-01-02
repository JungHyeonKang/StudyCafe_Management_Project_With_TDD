package com.kang.studyCafe.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.studyCafe.api.ControllerTestSupport;
import com.kang.studyCafe.api.service.desk.DeskService;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.desk.DeskRepository;
import com.kang.studyCafe.domain.desk.DeskStatus;
import com.kang.studyCafe.domain.desk.DeskType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.kang.studyCafe.domain.desk.DeskStatus.*;
import static com.kang.studyCafe.domain.desk.DeskType.*;
import static org.junit.jupiter.api.Assertions.*;

class DeskControllerTest extends ControllerTestSupport{


    @DisplayName("예약가능한 좌석을 조회한다")
    @Test
    public void getAvailableDesks() throws Exception {
        //given

        Mockito.when(deskService.getAvailableDeskList()).thenReturn(List.of());

        //when /then
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/desks/display")
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("OK"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray());

    }

}