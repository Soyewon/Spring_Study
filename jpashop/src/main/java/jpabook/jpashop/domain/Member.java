package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // pk
    private Long id; // 회원 아이디

    private String name; // 회원명

    @Embedded // 내장타입
    private Address address;

    @OneToMany(mappedBy = "member") // order 테이블에 있는 member 필드에 의해 맵핑되었다
    private List<Order> orders = new ArrayList<>();
}
