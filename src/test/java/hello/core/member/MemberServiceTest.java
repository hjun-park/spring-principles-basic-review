package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.BeforeEach;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }
}
