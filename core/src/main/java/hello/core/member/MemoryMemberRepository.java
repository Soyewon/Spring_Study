package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// 구현체
// 설계상 다른 패키지에 넣는 것이 좋으나, 예제연습이므로 member 패키지에 넣음
public class MemoryMemberRepository implements  MemberRepository{

    // 동시성 이슈 때문에 실무에서는 ConcurrentHashMap 쓰는거 추천
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
