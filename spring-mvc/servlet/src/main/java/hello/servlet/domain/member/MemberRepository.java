package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 실무에서는 ConcurrentHashMap, AtomicLong을 사용하여 동시성 문제를 고려해야 한다.
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // static으로 했기 때문에 MemberRepository가 아무리 많아도 얘는 하나만 생성됨.

    // 싱글톤 사용
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    // private으로 아무나 생성하지 못하도록
    private MemberRepository() {

    }

    // 멤버 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store.values()를 꺼내서 new ArrayList에 복사해 전달.
        // store 내부의 값을 건들고 싶지 않기 때문
    }

    public void clearStore() {
        store.clear();
    }
}
