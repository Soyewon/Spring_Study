package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    // 외부에서 넣어주도록 바꿔주기
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // * 회원가입
    public Long join(Member member) {

        // 시간 측정하기
        long start = System.currentTimeMillis();
        try{
        // 같은 이름이 있는 중복 회원은 안된다!
        validateDuplicateMember(member); // 중복 회원 검증

        // 중복 회원 검증 통과 시,
        memberRepository.save(member);
        return member.getId();} finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = "+timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 출력
                });
    }

    // * 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // * 한명 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
