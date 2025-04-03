package com.example.todo.config;

import com.example.todo.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {//servlet인 LoginFilter을 spring boot에서 의존성 주입등 편하게 사용하기 위해 bean으로 등록하려고 만든 것

    @Bean//매서드로 bean 등록(@Autowired로 생성자 주입)
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        //FilterRegistrationBean 역할
        //spring boot에서 제공하는 클래스로, Servlet Filter을 spring이 관리할 수 있도록 등록하는 클래스이다.
        //filter의 순서, url패턴 설정을 다룸
        FilterRegistrationBean<LoginFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
       //filterFilterRegistrationBean을 클래스로 선언하면 필터를 관리할 수 있다.
        filterFilterRegistrationBean.setFilter(new LoginFilter());
        //filterFilterRegistrationBean에 내가 만든 LoginFilter클래스를 넣는다.
        filterFilterRegistrationBean.setOrder(1);
        //필터 실행 순서를 정한다.
        filterFilterRegistrationBean.addUrlPatterns("/*");
        //필터를 적용할 url 패턴을 지정한다.
        return filterFilterRegistrationBean;
        //완성된 필터 설정을 spring에서 관리하도록 반환한다. 매서드의 반환값을 spring bean으로 등록

        //로그인 필터를 Bean으로 등록하기 위해 FilterRegistrationBean의 제네릭 타입을 LoginFilter로 지정한 후, 객체를 생성하고 그 안에 LoginFilter를 설정하는 방식으로 Bean에 등록하는 거다.. 그래서 이를 설정할 Config 클래스를 만드는 거다.
    }


}
