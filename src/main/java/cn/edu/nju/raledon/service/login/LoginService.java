package cn.edu.nju.raledon.service.login;

import cn.edu.nju.raledon.model.user.User;
import cn.edu.nju.raledon.controller.login.LoginBean;

/**
 * Created by rale on 7/12/17.
 * 登录模块，包括登录和注册
 */
public interface LoginService {

    /**
     * 输入ID和密码进行登录，ID可以为电话号码和名称和编号
     * @param loginBean
     * @return
     */
    User login(LoginBean loginBean);

}
