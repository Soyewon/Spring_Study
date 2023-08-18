package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// /v1 하위의 어떤것이 들어와도 일단 이 서블릿이 무조건 호출됨.
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        // 매핑 정보 담기
        controllerMap.put("front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); // Request URI를 꺼낼 수 있음. ex) front-controller/v1/members

        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller == null) { // 없을 경우
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //  그냥 return
        }

        // 잘 찾아서 호출이 된 경우,
        controller.process(request, response); // interface 찾아서 호출
    }
}
