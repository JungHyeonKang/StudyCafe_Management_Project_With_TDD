package com.kang.studyCafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

/*    controller 테스트 시, @WebMvcTest를 사용하는데
    @WebMvcTest 사용 시 SpringMvc 컴포넌트들만 적용한다.
    스프링 관련 모든 자동 구성이 비활성화 되고, MVC테스트와 관련된 구성만 적용된다.
    ex) @Controller, @ControllerAdvice, @JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurer,
    HandlerMethodArgumentResolve 등이 적용
    거기에 @Component, @Service , @Repository 도 적용이 안됨.
    @Controller를 포함한 SpringMVC레벨의 컴포넌트들이 적용된다.
    그래서 @EnableJpaAuditing 도 적용이 안되므로 빈이 주입되지 않아 테스트 시,
     JPA metamodel must not be empty 에러가 발생하므로 따로 config 처리
    */
}
