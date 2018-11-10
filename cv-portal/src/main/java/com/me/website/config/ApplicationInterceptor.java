package com.me.website.config;


import com.me.website.common.vo.RequestVo;
import com.me.website.service.AppLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class ApplicationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AppLoggerService loggerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Add pre request processing like authentication or proxy layer
        this.loggerService.saveRequest(new RequestVo(request.getRequestURI(),
                request.getMethod(),
                request.getHeader("token") != null ? request.getHeader("token").toString() : "",
                request.getHeader("Content-Type") != null ? request.getHeader("Content-Type").toString() : ""));

        return super.preHandle(request, response, handler);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
