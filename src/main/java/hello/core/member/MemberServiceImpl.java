package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // Appconfig 통해서 생성자 주입
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 검증 용도 테스트 코드 추가
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
