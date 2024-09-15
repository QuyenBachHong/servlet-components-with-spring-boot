package me.quyen.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
public class CustomHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("boolean CUSTOM_HANDLER_INTERCEPTOR.preHandle(HttpServletRequest,HttpServletResponse,Object) throws Exception");
        log.info("request-url:{}",request.getRequestURL().toString());
        return true;
//        return HandlerInterceptor.super.preHandle(request, response, handler);

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("CUSTOM_HANDLER_INTERCEPTOR.postHandle(HttpServletRequest,HttpServletResponse,Object,ModelAndView) throws Exception");
        log.info("request-url:{}",request.getRequestURL().toString());
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("CUSTOM_HANDLER_INTERCEPTOR.postHandle(HttpServletRequest,HttpServletResponse,Object,Exception) throws Exception");
        log.info("request-url:{}",request.getRequestURL().toString());
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
