package com.yzk.inteceptor;

import com.yzk.entity.User;
import com.yzk.util.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${user.id}")
    private Long id;
    @Value("${user.username}")
    private String username;
    @Value("${user.password}")
    private String password;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //打印请求信息
        log.info("---------loginInterceptor开始-----------");
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("requestStartTime");
        log.info("----------------loginInterceptor结束，耗时：${}ms",System.currentTimeMillis()-startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //如果不删除ThreadLocal中用完的信息会有内存泄露的风险
        UserThreadLocal.clear();
        log.info("loginInterceptor 结束");
    }
}
