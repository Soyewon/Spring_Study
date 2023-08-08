package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!"); // data라는 key에 hello라는 값을 넣는다
        return "hello"; // viewname을 hello.html로 넘겨준다
    }
}
