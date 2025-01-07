package com.kang.studyCafe.api.service.checkin;

import com.kang.studyCafe.api.IntegrationTestSupport;
import com.kang.studyCafe.api.service.checkin.request.CheckInCreateServiceRequest;
import com.kang.studyCafe.api.service.checkin.response.CheckInResponse;
import com.kang.studyCafe.domain.checkin.CheckIn;
import com.kang.studyCafe.domain.checkin.CheckInRepository;
import com.kang.studyCafe.domain.desk.Desk;
import com.kang.studyCafe.domain.desk.DeskRepository;
import com.kang.studyCafe.domain.desk.DeskStatus;
import com.kang.studyCafe.domain.desk.DeskType;
import com.kang.studyCafe.domain.ticket.Ticket;
import com.kang.studyCafe.domain.ticket.TicketRepository;
import com.kang.studyCafe.domain.ticket.TicketStatus;
import com.kang.studyCafe.domain.ticket.TicketType;
import com.kang.studyCafe.domain.user.User;
import com.kang.studyCafe.domain.user.UserRepository;
import com.kang.studyCafe.domain.user.UserStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.kang.studyCafe.domain.desk.DeskStatus.*;
import static com.kang.studyCafe.domain.desk.DeskType.*;
import static org.assertj.core.api.Assertions.*;


@Transactional
class CheckInServiceTest extends IntegrationTestSupport {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private DeskRepository deskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    private static final LocalDateTime checkInTime = LocalDateTime.now();


    @DisplayName("이용 가능한 Desk에 티켓을 가지고 있는 check_out 상태인 회원은 checkIn 할 수 있다.")
    @Test
    public void createCheckInTest() throws Exception {
        //given
        Desk desk = createDesk(1,SINGLE, AVAILABLE);
        Desk savedDesk = deskRepository.save(desk);


        Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
        Ticket savedTicket = ticketRepository.save(ticket);

        User user = User.createUser("타노스", UserStatus.CHECK_OUT, savedTicket);
        User savedUser = userRepository.save(user);

        CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                .userId(savedUser.getId())
                .deskId(savedDesk.getId())
                .build();

        //when
        CheckInResponse checkInResponse = checkInService.createCheckIn(request, checkInTime);

        //then

        assertThat(checkInResponse.getId()).isNotNull();
        assertThat(checkInResponse.getCheckInTime()).isEqualTo(checkInTime);
        assertThat(checkInResponse.getExpectedCheckOutTime()).isEqualTo(checkInTime.plusHours(savedUser.getTicket().getRemainingTime()));
    }

    @DisplayName("이미 사용중인 Desk에 티켓을 가지고 있는 check_out 상태인 회원이 checkIn을 하면 IllegalArgumentException 에러가 난다.")
    @Test
    public void CheckInToInUseDesk() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, IN_USE);
        Desk savedDesk = deskRepository.save(desk);

        Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
        Ticket savedTicket = ticketRepository.save(ticket);
        ticketRepository.save(savedTicket);
        User user = User.createUser("타노스", UserStatus.CHECK_OUT, ticket);
        User savedUser = userRepository.save(user);

        CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                .userId(savedUser.getId())
                .deskId(savedDesk.getId())
                .build();


        //when then
        assertThatThrownBy((() -> checkInService.createCheckIn(request, checkInTime)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("이미 사용중인 좌석 입니다.");
    }

    @DisplayName("사용불가능한 Desk에 티켓을 가지고 있는 check_out 상태인 회원이 checkIn을 하면 IllegalArgumentException 에러가 난다.")
    @Test
    public void CheckInToUnavailableDesk() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, UNAVAILABLE);
        Desk savedDesk = deskRepository.save(desk);

        Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
        Ticket savedTicket = ticketRepository.save(ticket);
        ticketRepository.save(savedTicket);
        User user = User.createUser("타노스", UserStatus.CHECK_OUT, ticket);
        User savedUser = userRepository.save(user);

        CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                .userId(savedUser.getId())
                .deskId(savedDesk.getId())
                .build();


        //when then
        assertThatThrownBy((() -> checkInService.createCheckIn(request, checkInTime)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("사용 불가 좌석 입니다.");
    }

    @DisplayName("이미 check_in 상태인 회원이 checkIn을 하면 IllegalArgumentException 에러가 난다.")
    @Test
    public void CheckInFromCheckInUser() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, AVAILABLE);
        Desk savedDesk = deskRepository.save(desk);

        Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
        Ticket savedTicket = ticketRepository.save(ticket);
        ticketRepository.save(savedTicket);

        User user = User.createUser("타노스", UserStatus.CHECK_IN, ticket);
        User savedUser = userRepository.save(user);

        CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                .userId(savedUser.getId())
                .deskId(savedDesk.getId())
                .build();


        //when then
        assertThatThrownBy((() -> checkInService.createCheckIn(request, checkInTime)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("이미 입실중인 회원 입니다.");
    }

    @DisplayName("이미 check_in 상태인 회원이 checkIn을 하면 IllegalArgumentException 에러가 난다.")
    @Test
    public void CheckInFromNonExistedUser() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, AVAILABLE);
        Desk savedDesk = deskRepository.save(desk);

        Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
        Ticket savedTicket = ticketRepository.save(ticket);
        ticketRepository.save(savedTicket);

        User user = User.createUser("타노스", UserStatus.CHECK_IN, ticket);
        User savedUser = userRepository.save(user);

        CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                .userId(savedUser.getId() + 1000)
                .deskId(savedDesk.getId())
                .build();


        //when then
        assertThatThrownBy((() -> checkInService.createCheckIn(request, checkInTime)))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("존재하지 않는 회원입니다");
    }

    @DisplayName("체크인 시, 해당 Desk는 In_USE 상태로 변경된다.")
    @Test
    public void updateDeskStatusToInUse() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, AVAILABLE);
        Desk savedDesk = deskRepository.save(desk);

        Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
        Ticket savedTicket = ticketRepository.save(ticket);
        ticketRepository.save(savedTicket);

        User user = User.createUser("타노스", UserStatus.CHECK_OUT, ticket);
        User savedUser = userRepository.save(user);

        CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                .userId(savedUser.getId())
                .deskId(savedDesk.getId())
                .build();

        //when
        CheckInResponse checkInResponse = checkInService.createCheckIn(request, checkInTime);

        //then

        assertThat(checkInResponse.getDeskResponse().getDeskStatus()).isEqualTo(IN_USE);

    }

    @DisplayName("체크인 시, 해당 User는 Checkout -> checkIn 상태로 변경된다.")
    @Test
    public void updateUserStatusToCheckIn() throws Exception {
        //given
        Desk desk = createDesk(1,LAPTOP, AVAILABLE);
        Desk savedDesk = deskRepository.save(desk);

        Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
        Ticket savedTicket = ticketRepository.save(ticket);
        ticketRepository.save(savedTicket);

        User user = User.createUser("타노스", UserStatus.CHECK_OUT, ticket);
        User savedUser = userRepository.save(user);

        CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                .userId(savedUser.getId())
                .deskId(savedDesk.getId())
                .build();

        //when
        CheckInResponse checkInResponse = checkInService.createCheckIn(request, checkInTime);

        //then
        assertThat(checkInResponse.getUserResponse().getUserStatus()).isEqualTo(UserStatus.CHECK_IN);

    }

    @DisplayName("동시에 같은 desk를 체크인 시, 먼저 접근한 회원이 체크인 되고 나머지 회원들은 IllegalException을 받는다")
    @Test
    @Disabled
    public void checkInAtTheSameTime() throws Exception {
        int threadCount = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        //when
        executorService.execute(()->{
            //given
            Desk desk = createDesk(1,LAPTOP, AVAILABLE);
            Desk savedDesk = deskRepository.save(desk);

            Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
            Ticket savedTicket = ticketRepository.save(ticket);

            User user = User.createUser("타노스", UserStatus.CHECK_OUT, savedTicket);
            User user2 = User.createUser("성기훈", UserStatus.CHECK_OUT, savedTicket);
            User user3 = User.createUser("오일영", UserStatus.CHECK_OUT, savedTicket);
            List<User> users = userRepository.saveAll(List.of(user, user2, user3));

            try {
                CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
                        .userId(1L)
                        .deskId(savedDesk.getId())
                        .build();
                checkInService.createCheckIn(request, checkInTime);
            } finally {
                countDownLatch.countDown();
            }

        });

//        executorService.execute(()->{
//            //given
//            Optional<Desk> desk = deskRepository.findById(1L);
//            Ticket ticket = Ticket.createTicket(TicketStatus.AVAILABLE, TicketType.TIME_TICKET_50_HOURS);
//            Ticket savedTicket = ticketRepository.save(ticket);
//
//            User user = User.createUser("타노스", UserStatus.CHECK_OUT, savedTicket);
//            User user2 = User.createUser("성기훈", UserStatus.CHECK_OUT, savedTicket);
//            User user3 = User.createUser("오일영", UserStatus.CHECK_OUT, savedTicket);
//            List<User> users = userRepository.saveAll(List.of(user, user2, user3));
//
//            try {
//                CheckInCreateServiceRequest request = CheckInCreateServiceRequest.builder()
//                        .userId(2L)
//                        .deskId(desk.get().getId())
//                        .build();
//                checkInService.createCheckIn(request, checkInTime);
//            } finally {
//                countDownLatch.countDown();
//            }
//
//        });


        countDownLatch.await();

        //then
        List<CheckIn> all = checkInRepository.findAll();
        assertThat(all).hasSize(1);

    }

    private Desk createDesk(int deskNumber, DeskType deskType, DeskStatus deskStatus) {

        return Desk.builder()
                .deskType(deskType)
                .deskNumber(deskNumber)
                .deskStatus(deskStatus)
                .build();
    }



}