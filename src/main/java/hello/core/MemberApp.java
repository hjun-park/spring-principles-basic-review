package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 애플리케이션 단에서 테스트 코드
public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appconfig = new AppConfig();
//        MemberService memberService = appconfig.memberService();

        // JAVA -> spring 적용
        /*
            ApplicationContext : 스프링 컨테이너
            기존에는 개발자가 AppConfig를 사용해서 직접 생성하고 DI를 했지만 (위에처럼)
            이제부터는 스프링 컨테이너가 관리해준다.

            getBean()을 사용하여 스프링 빈 객체를 찾을 수 있다.
            찾은 빈을 통해서 인터페이스 (MemberService)에 직접 주입할 수 있다.
         */

        // 컨테이너 생성
        // new Annotation.. : ApplicationContext 인터페이스의 구현체
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("New Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
