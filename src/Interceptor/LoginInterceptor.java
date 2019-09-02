package Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: MachineLearning
 * @description: 针对用户非登陆请求的是否已经登录进行拦截
 * @author: Mr.Sun
 * @create: 2019-05-13 14:32
 **/

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response,
                              Object o) throws Exception {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if(userid!=null){
            return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/user/login");
        }
        return false;
    }

    @Override
    public void postHandle( HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                            Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
