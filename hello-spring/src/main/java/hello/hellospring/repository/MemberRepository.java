package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // id가 없다면 Null 반환 시 optional로 감싸서 반환.
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
