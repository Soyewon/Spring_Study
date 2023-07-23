package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // /hello로 들어오면 얘를 호출.
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }
}
