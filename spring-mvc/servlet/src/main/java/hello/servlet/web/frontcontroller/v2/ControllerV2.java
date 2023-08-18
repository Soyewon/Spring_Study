package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // ControllerV1과 똑같은데 반환값이 void가 아닌 MyView인것만 다름.
    // 왜냐면 모든 컨트롤러가 view를 반환하기 때문(jsp로 이동)
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
