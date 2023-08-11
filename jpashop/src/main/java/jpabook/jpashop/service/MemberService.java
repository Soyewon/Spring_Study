package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional(readOnly = false) // 얘는 readOnly 하면 안되므로 따로 설정해주어 우선권을 가지도록 한다.
    public Long join(Member member) {

        // 중복 회원 검증
        validateDuplicateMember(member);
        // 회원 검증이 완료됐다면 가입
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 방지
    private void validateDuplicateMember(Member member) {
        // Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());// 같은 이름이 있는지 찾기
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전제 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // id로 단건조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
