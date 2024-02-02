//package com.softwareag.cafeteria.config;
//
//import com.softwareag.cafeteria.util.JwtUtils;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.WebRequestInterceptor;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
//
//import java.util.List;
//
//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
////        String auth =request.getHeader("authorization");
//
//
////        if(!request.getRequestURI().contains("login")&&!request.getMethod().equalsIgnoreCase("OPTIONS")){
////            Claims claims = jwtUtils.verify(auth);
////
////            request.setAttribute("employeeId",claims.get("employeeId"));
////            request.setAttribute("employeeName",claims.get("employeeName"));
////            request.setAttribute("employeeRole",claims.get("employeeRole"));
////            request.setAttribute("employeeBalance",claims.get("employeeBalance"));
////
////
////        }
//        return HandlerInterceptor.super.preHandle(request,response,handler);
//    }
//}
