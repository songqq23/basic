package com.example.demodb.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.demodb.Result.Result;
import com.example.demodb.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.util.StringUtils;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DEMOFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("过滤器在初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        HttpServletResponse resp=(HttpServletResponse)servletResponse;
        //获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求路径：{}",url);
        //只有登录的请求不拦截，其他请求都拦截
        if(url.contains("login")){
            log.info("登录操作，直接放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //获取令牌
        String jwt = req.getHeader("token");
        //令牌为空，就是没登陆
        if(!StringUtils.hasLength(jwt)){
            log.info("token为空，未登录");
            Result error = Result.error("用户未登录");
            //手动转换，对象-》json
            String notLogin = JSONObject.toJSONString(error);
            //resp.getWriter()是输出流
            resp.getWriter().write(notLogin);
            return;
        }
        //解析JWT
        JwtUtils.parseJwt(jwt);
//        try{
//            JwtUtils.parseJwt(jwt);
//        }catch (Exception e){
//            e.printStackTrace();
//            log.info("令牌解析失败，未登录错误");
//
//            Result error = Result.error("notLoginError");
//            //手动转换，对象-》json
//            String notLogin = JSONObject.toJSONString(error);
//            //resp.getWriter()是输出流
//            resp.getWriter().write(notLogin);
//            return;
//        }

        //放行
        log.info("令牌合法");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
