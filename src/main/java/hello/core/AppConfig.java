package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // memberService와 orderService에 생성자를 두어야 만들 수 있음
    // AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성하고
    // AppConfig는 생성한 객체 인스턴스의 참조를 생성자 통해서 주입한다.


    /*
        리팩토링 전
     */
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(
//                new MemoryMemberRepository(),
//                new FixDiscountPolicy());
//    }

    /*
        리팩토링 후
     */

//    // 생성 ( 저장방식, 할인정책을 여기서만 수정하면 되므로 매우 간편함
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    public DiscountPolicy discountPolicy() {
//        // return new FixDiscountPolicy();
//        // 할인정책을 변동으로 바꾸려면 아래와 같이 수정하면된다.
//        return new RateDiscountPolicy();
//    }
//
//
//    // 주입함
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(
//                memberRepository(),
//                discountPolicy());
//    }



     /*
        JAVA 기반 -> 스프링 기반 변경
     */

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }


}
