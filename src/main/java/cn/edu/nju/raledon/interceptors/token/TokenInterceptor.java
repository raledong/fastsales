package cn.edu.nju.raledon.interceptors.token;

import cn.edu.nju.raledon.controller.product.bean.ProductBean;
import cn.edu.nju.raledon.model.product.Product;
import cn.edu.nju.raledon.properties.SessionProperties;
import cn.edu.nju.raledon.util.UUIDGenerator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by rale on 7/13/17.
 * 令牌拦截器
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(TokenInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("what the fuck");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);

            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession(false).setAttribute(SessionProperties.TOKEN, UUIDGenerator.generateUUID());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request, SessionProperties.TOKEN)) {
                        return false;
                    }
                    request.getSession(false).removeAttribute(SessionProperties.TOKEN);
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request, String tokenName) {
        String serverToken = (String) request.getSession(false).getAttribute(tokenName);
        System.out.println("serverToken"+serverToken);
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter("token");
        System.out.println(clientToken);

        if (clientToken == null) {
            return true;
        }

        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }
}
