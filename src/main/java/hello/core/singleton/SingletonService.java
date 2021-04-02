package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonService {

    /*
        SingletonTest와는 다른 방식, 싱글톤을 이용하여 객체 1개만 생성
     */

    // 1. static 영역에 객체를 딱 1개만 생성해 둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 객체 인스턴스가 필요하면 이 static 메소드 통해서 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자는 private로 해서 외부에서 new 키워드를 이용한 객체 생성을 막는다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직");
    }


    /*
    SingletonService 파일 ( 싱글톤 패턴 ) 사용하는 테스트 코드
    */

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {

        // private로 생성자를 막아두었기 때문에 아래 코드를 사용하면 오류 발생
        //new SingletonService();

        // 1. 조회 : 호출할 때마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();

        // 2. 조회 : 호출할 때마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인
        assertThat(singletonService1).isSameAs(singletonService2);
        singletonService1.logic();
    }

    /*
        싱글톤 패턴의 문제점
            1) 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
            2) 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP 위반
            3) 클라이언트가 구체 클래스에 의존함에따라 OCP 원칙을 위반할 가능성이 높다.
            4) 테스트하기 어렵다.
            5) 내부 속성을 변경하거나 초기화가 어렵다.
            6) private 생성자로 자식 클래스 만들기 어렵다.
            7) ==> 결론적으로 유연성이 떨어지며 안티패턴으로 불리기도 한다.

            ==> 그래서 싱글톤 컨테이너가 나오게 되었다.
     */



    /*
        싱글톤 컨테이너
          1) 싱글톤 패턴 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리
          2) 싱글톤 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있음
          3) 지저분한 코드가 들어가지 않아도 됨
     */
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할 때마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }






}
