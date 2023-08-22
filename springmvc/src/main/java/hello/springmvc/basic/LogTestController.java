package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
// @Controller는 뷰 이름을 반걉
public class LogTestController {
    // private final Logger log = LoggerFactory.getLogger(getClass());
    // slf4j 애노테이션이 코드를 자동으로 넣어줌

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // 로그가 현재 어떤 상태인지 알 수 있음.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        // 위의 두개는 로그에 남지 않음.
        log.info("debug log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
