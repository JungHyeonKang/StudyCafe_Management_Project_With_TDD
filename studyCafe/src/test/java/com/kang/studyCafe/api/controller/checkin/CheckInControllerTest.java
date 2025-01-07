package com.kang.studyCafe.api.controller.checkin;

import com.kang.studyCafe.api.ControllerTestSupport;
import com.kang.studyCafe.api.controller.checkin.request.CheckInCreateRequest;
import com.kang.studyCafe.api.service.checkin.request.CheckInCreateServiceRequest;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.ticket.Ticket;
import com.kang.studyCafe.domain.ticket.TicketStatus;
import com.kang.studyCafe.domain.ticket.TicketType;
import com.kang.studyCafe.domain.user.User;
import com.kang.studyCafe.domain.user.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static com.kang.studyCafe.domain.desk.DeskStatus.AVAILABLE;
import static com.kang.studyCafe.domain.desk.DeskType.LAPTOP;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class CheckInControllerTest extends ControllerTestSupport {

    @DisplayName("신규 체크인을 생성한다")
    @Test
    public void createCheckIn() throws Exception {
        //given
        CheckInCreateRequest request = CheckInCreateRequest.builder()
                .deskId(1L)
                .userId(1L)
                .build();

        //when then

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/checkIn/new")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("OK"));

    }

    @DisplayName("신규 체크인을 생성 시, 필수 파라메터인 userId, deskId를 주지 않으면 에러가 난다.")
    @Test
    public void createCheckInWithoutParam() throws Exception {
        //given
        CheckInCreateRequest request = CheckInCreateRequest.builder()
                .deskId(null)
                .userId(1L)
                .build();

        //when then

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/checkIn/new")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("좌석 정보는 필수 값 입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());

    }
}