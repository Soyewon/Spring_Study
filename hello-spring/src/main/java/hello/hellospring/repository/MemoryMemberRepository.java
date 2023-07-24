package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 고려 해줘야됨
    private static long sequence = 0L; // key값 생성해줌.
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member의 id값 세팅
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null일 경우를 대비해 감싸서 준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 루프를 돌면서
                .filter(member -> member.getName().equals(name)) // member.getname이 parameter로 넘어온 getname과 같은지
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // value: store에 있는 values가 멤버들이므로 map을 array로 변환해줌.
    }
}
