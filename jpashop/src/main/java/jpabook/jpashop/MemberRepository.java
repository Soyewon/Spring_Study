package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // component scan의 대상이 되어 자동으로 스캔
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 등록
    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 커맨드와 쿼리를 분리
    }

    // 멤버 조회
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
