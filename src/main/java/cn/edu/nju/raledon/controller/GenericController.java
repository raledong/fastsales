package cn.edu.nju.raledon.controller;

import cn.edu.nju.raledon.properties.SessionProperties;
import cn.edu.nju.raledon.util.UUIDGenerator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rale on 7/21/17.
 */
public abstract class GenericController {
    protected void addToken(HttpServletRequest servletRequest){
        servletRequest.getSession(false).setAttribute(SessionProperties.TOKEN, UUIDGenerator.generateUUID());
    }

    protected void removeToken(HttpServletRequest servletRequest){
        servletRequest.getSession(false).removeAttribute(SessionProperties.TOKEN);
    }

    protected boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute(SessionProperties.TOKEN);
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter(SessionProperties.TOKEN);
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
