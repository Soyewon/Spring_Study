package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id; // db에 저장하면 id가 발급되도록
    private String username;
    private int age;

    // 기본 생성자
    public Member() {

    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
