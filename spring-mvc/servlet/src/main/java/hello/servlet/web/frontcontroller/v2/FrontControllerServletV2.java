package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet2", urlPatterns = "/front-controller/v2/*")
// /v1 하위의 어떤것이 들어와도 일단 이 서블릿이 무조건 호출됨.
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        // 매핑 정보 담기
        controllerMap.put("front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI(); // Request URI를 꺼낼 수 있음. ex) front-controller/v1/members

        ControllerV2 controller = controllerMap.get(requestURI);
        if(controller == null) { // 없을 경우
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //  그냥 return
        }

        // 잘 찾아서 호출이 된 경우,
        MyView view = controller.process(request, response);// interface 찾아서 호출
        view.render(request, response);
    }
}
