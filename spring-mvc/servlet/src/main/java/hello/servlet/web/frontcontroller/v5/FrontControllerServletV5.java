package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    /**
     * 기존 코드 : private Map<String, ControllerV3> controllerMap = new HashMap<>();
     * 얘는 다 지원해야 하므로 Object를 넣음
     */
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        // 매핑 정보
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("front-controller/v5/v3/members", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // ex. front-controller/v5/v3/members/new-form 가 들어왔다고 가정
        // MemberFormControllerV3() 반환
        Object handler = getHandler(request); // 핸들러 매핑 정보 (1번)
        if(handler == null) { // 없을 경우
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //  그냥 return
        }

        // ControllerV3HandlerAdapter() 반환
        MyHandlerAdapter adapter = getHandlerAdapter(handler); // 2. 핸들러 어댑터 찾아오기

        ModelView mv = adapter.handle(request, response, handler); // 3. 4. 5. 과정

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName); // 6. 7.

        view.render(mv.getModel(), request, response); // 8.
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.support(handler)) {
                return adapter; // 서포트 할 경우 선택됨
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다.handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI(); // Request URI를 꺼낼 수 있음. ex) front-controller/v1/members
        return handlerMappingMap.get(requestURI);
    }

    private static MyView viewResolver(String viewName) {
        // /WEB-INF/views/new-form.jsp
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
