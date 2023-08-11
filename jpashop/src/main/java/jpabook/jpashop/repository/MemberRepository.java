package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 컴포넌트 스캔에 의해 자동으로 스프링 빈으로 관리됨
public class MemberRepository {

    @PersistenceContext // 스프링이 entity manager를 만들어서 주입해줌
    private EntityManager em;

    public void save(Member member) {
        em.persist(member); // jpa가 member를 저장하는 로직
    }

    // 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id); // member의 id를 찾아서 반환
    }

    // 리스트 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // 쿼리문, 반환타입을 넣어주면 Member를 list로 반환해줌
    }

    // 이름으로 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
