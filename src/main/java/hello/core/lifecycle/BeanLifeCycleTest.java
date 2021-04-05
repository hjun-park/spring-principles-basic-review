package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    1. 스프링 빈의 이벤트 라이프사이클
        스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 ->
        -> 초기화 콜백(빈 생성되고 의존관계 주입 완료 된 후의 호출) -> 사용 -> 소멸전 콜백 -> 스프링 종료.

   2. 스프링은 크게 3가지 방법으로 빈 생명주기 콜백 지원
        a) 인터페이스
        b) 설정 정보에 초기화 메소드, 종료 메소드 지정
        c) @PostConstruct, @PreDestroy 애노테이션 지원 ==> 가장 많이 사용하고 NetworkClient 예제에 등록함
 */

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        // 생성자가 호출되지만 예상했던 것과 다르게 url 정보 없이 호출이 되는 것을 볼 수 있음
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close(); // 스프링 컨테이너를 종료, 이 때 ConfigurableApplicationContext 필요
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");

            return networkClient;
        }
    }
}
