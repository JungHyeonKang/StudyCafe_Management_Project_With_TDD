# 개요
TDD 개발 연습을 위한 간단한 스터디 카페 입실 시스템 토이 프로젝트 구축.

목표 : TDD 기반 개발 연습을 하여 테스트 코드 작성 역량 향상.

# 요구사항
## 좌석
  - [x] 입실 가능한 좌석 조회 할 수 있다.
  - [x] 좌석 종류, 좌석 상태(이용중, 이용 가능, 사용 불가) 생성 
    - "이용 가능" 상태인 좌석만 예약 가능
       
## 이용권
  - [x] 시간권, 당일권 이용권 생성
  - [x] 이용권 시간 설정
      
## 입실
  - [x] "이용 가능" 상태인 좌석만 입실 할 수 있다.
  - [x] 입실된 좌석은 "이용중" 상태가 된다
  - [x] 입실한 회원은 "CHECK_IN" 상태가 된다.
  - [x] 입실 성공 시, 이용 시작 시간, 이용 종료 예정 시간을 생성한다.
  - [x] 입실 성공 시, 예약 관련 문자를 전송한다.
        - 비즈니스 로직은 로그로 대체, 문자 전송 이력만 DB에서 관리
        - 테스트는 Mock으로 진행
  


