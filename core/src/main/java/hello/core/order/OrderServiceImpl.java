package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보 조회
        // 단일 책임 원칙을 잘 지킨 것. 할인에 대한 수정이 필요하면, 할인 관련된것만 수정하면 되기 때문.
        int discountPrice = discountPolicy.discount(member, itemPrice); // 넘겨진 회원정보에 대해 할인 적용

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
