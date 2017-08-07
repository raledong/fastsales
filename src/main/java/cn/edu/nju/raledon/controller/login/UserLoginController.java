package cn.edu.nju.raledon.controller.login;

import cn.edu.nju.raledon.model.user.User;
import cn.edu.nju.raledon.properties.SessionProperties;
import cn.edu.nju.raledon.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by rale on 4/30/17.
 * 用户登录和退出登录接口
 */
@Controller
public class UserLoginController{

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        LoginBean loginBean = new LoginBean();
        modelMap.addAttribute("loginBean", loginBean);
        return new ModelAndView("login", modelMap);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpSession session, @ModelAttribute("loginBean")LoginBean loginBean){
        request.removeAttribute(SessionProperties.MESSAGE);
        User user = loginService.login(loginBean);
        System.out.println(123);

        if(user == null){
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("loginBean", loginBean);
            request.setAttribute(SessionProperties.MESSAGE, LoginMessage.INVALID_CREDENTIAL.getInfo());
            return new ModelAndView("login", modelMap);
        }
        session.setAttribute(SessionProperties.USER_ID, user.getUserId());
        session.setAttribute(SessionProperties.USER_NAME, user.getName());
        session.setAttribute(SessionProperties.USER_POSITION, user.getRole());
        return new ModelAndView("/index");
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session, HttpServletRequest request){
        if(session.getAttribute(SessionProperties.USER_ID) != null){
            session.removeAttribute(SessionProperties.USER_ID);
            session.removeAttribute(SessionProperties.USER_NAME);
            session.removeAttribute(SessionProperties.USER_POSITION);
            request.setAttribute(SessionProperties.MESSAGE, SessionProperties.LOGOUT_SUCCESS);
        }else {
            request.setAttribute(SessionProperties.MESSAGE, SessionProperties.ALREADY_LOGOUT);
        }
        session.invalidate();
        return "redirect:/login";
    }
    public String login(String name, String password){
        return "";
    }

}
