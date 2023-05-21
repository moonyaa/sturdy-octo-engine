package com.itheima.demo.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        String uri=request.getRequestURI();
        if(uri.indexOf("/login")>=0){
            return true;
        }
        HttpSession session =request.getSession();
        if(session.getAttribute("user_session")!=null){
            return true;
        }

        request.setAttribute("msg","您还没有登录，请先登录！");
        request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request,response);
        return false;
    }
}
