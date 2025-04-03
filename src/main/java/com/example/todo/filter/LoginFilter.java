package com.example.todo.filter;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

//http 메세지가 왔을 때 젤 처음 로그인 인증을 위해 만든 클래스, filter역할
//1. 세션 생성하고, ID 저장
//2. 이후에 들어올 때 유효성 검사
@Component//로그인 이후 요청 => 클라이언트 세션가지고 있음, 세션 유효성 검증을 filter에서 함
public class LoginFilter implements Filter {
    //인증하지 않고 그냥 들어갈 수 있는 url
    private static final List<String> excludeUrls = List.of("/login", "/users/user");

    //filter을 implements해야 doFilter()매서드를 강제로 가져와서 핉터 역할을 할 수 있다.
    @Override//들어오는 요청을 강제로 매핑함
    public void doFilter(ServletRequest servletRequest,//필터에서 요청을 가로챌 때 받는 변수, ServletRequest: 모든 종류의 요청을 다루는 인터페이스
                         ServletResponse servletResponse, //서버에 응답을 보낼 때 사용하는 변수
                         FilterChain filterChain)

            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest; //웹 프로토콜로 오는 요청을 받아야해서 다운 캐스팅, 이걸 해야 웹 프로토콜을 사용할 수 있다.
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String uri = httpServletRequest.getRequestURI();//요청한 url 경로 가져오기

        if (excludeUrls.contains(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);//이걸 요청하면, 다음 필터 또는 필터가 없으면 컨트롤러로 요청을 넘긴다. => request가 함.
            //컨트롤러 - 서비스 - 레파지토리 순으로 처리하고 반환한 값은 response로 받음
            return;
        }
        //로그인 확인 -> 들어온 값에 session 값이 있는지 확인
        HttpSession session = httpServletRequest.getSession(false);
        //getSession은 세션이 없으면, true면 세션을 생성함, 근데 우리는 없으면 if문으로 가서 되돌아가 하고 싶어서 false임
        if (session == null || session.getAttribute("userId") == null) {//||이건 앞에게 false면 뒤에까지 안 가고, true면 뒤로 넘어와서 다시 확인
            //session.getAttribute는 세션에서 ()안에 있는 것을 가져온다는 의미이다. 세션은 사용자 정보를 유지하기 위해 사용하는 저장소이다.
            //
            throw new RuntimeException("로그인 해주세요");
        }

        filterChain.doFilter(servletRequest, servletResponse);


    }
}
