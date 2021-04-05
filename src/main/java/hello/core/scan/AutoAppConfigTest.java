package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*

    AutoAppConfig 에는
        @ComponentScan 은 AutoAppConfig에 설정
        @Component를 클래스에 붙어 자동으로 스프링 빈 저장소(스프링 컨테이너)에 등록
        @Autowired를 이용하여 의존관계를 자동으로 주입

 */

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        // AppConfig와 유사하게 AutoAppConfig도 ApplicationContext를 사용,
        // 설정정보로 AutoAppConfig를 넘겨줌
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
