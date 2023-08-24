package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // get으로 호출
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 이렇게 할 경우, @Controller여도 @RestController와 같은 효과 -> 뷰 조회를 진행하지 않음
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody // 이렇게 할 경우, @Controller여도 @RestController와 같은 효과 -> 뷰 조회를 진행하지 않음
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 생략 가능(단, 변수명이 똑같아야 함)
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody // 이렇게 할 경우, @Controller여도 @RestController와 같은 효과 -> 뷰 조회를 진행하지 않음
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { // 요청 파라미터와 변수가 같을 경우 @RequestParam 도 생략 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // username이 없을 경우 오류가 남
            @RequestParam(required = false) Integer age) {
        // age가 없다면 null이 들어가야 함. int에는 null이 들어갈 수 없기 때문에, integer(객체형)으로

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, // username이 없을 경우 오류가 남.
            // 그러나 빈 문자 ex.username= 의 경우 default 값인 guest로 설정됨
            @RequestParam(required = false, defaultValue = "-1") int age) {
        // age가 없다면 null이 들어가야 함. int에는 null이 들어갈 수 없기 때문에, integer(객체형)으로

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
