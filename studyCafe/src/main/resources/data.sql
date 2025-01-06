insert into Desk(deskNumber, deskType, deskStatus)
values (1, 'LAPTOP', 'IN_USE'),
(2, 'SINGLE', 'AVAILABLE'),
(3, 'CAFE', 'UNAVAILABLE');

insert into Ticket(ticketType, ticketStatus, remainingTime)
values ('TIME_TICKET_50_HOURS', 'AVAILABLE', 50),
('TIME_TICKET_100_HOURS', 'AVAILABLE', 100),
('SINGLE_DAY_TICKET_2_HOURS', 'AVAILABLE', 2);

insert into User(userName, userStatus, ticket_id)
values ('타노스', 'CHECK_IN', 1),
('성기훈', 'CHECK_OUT', 2),
('오일남', 'CHECK_OUT', 3);

