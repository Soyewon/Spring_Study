package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.jdbcMemberRepository;
import hello.hellospring.repository.jdbcTemplateMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 멤버서비스를 스프링 빈에 등록해줌
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new jdbcMemberRepository(dataSource);
        return new jdbcTemplateMemberRepository(dataSource);
    }
}
