package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*
    싱글톤 스코프 빈 vs 프로토타입 스코프 빈
    1. 둘 다 생성 시점에 초기화 메소드가 실행되지만 프로토타입 빈은 스프링 컨테이너에서 빈 조회 시에만 생성되며 초기화 메소드도 실행
    2. 즉, 싱글톤 빈을 2번 조회하게 되면 완전히 다른 스프링 빈이 생성되고 초기화도 2번 수행된다.
    3. 싱글톤 빈의 경우 스프링 컨테이너 종료 시 종료 메소드 실행되지만
       프로토타입 빈은 종료 메소드가 실행되지 않는다. 즉, 종료 메소드 호출은 클라이언트가 직접 해 주어야 한다.
 */

public class PrototypeTest {
    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close(); //종료
    }
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}