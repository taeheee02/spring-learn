package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // private final로 설정된 필드에 대한 생성자 자동 생성
public class OrderServiceImpl implements OrderService {

    //생성자를 주입을 쓰면 private final을 사용할 수 있음 -> 생성자 코드를 잊었을 경우 컴파일 오류로 원인을 쉽게 찾을 수 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    // 필드 주입 : 사용하는 것이 권장되지 않음 <- 스프링 외부 환경에서 테스트& 변경 불가능, 따로 setter를 만들어어야 함
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        // 생성자 주입은 의존관계 불변을 보장
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //[테스트]
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
