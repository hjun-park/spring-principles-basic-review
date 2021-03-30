package hello.core.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/* Junit을 이용한 테스트 방식 */
class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "MemberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

}
