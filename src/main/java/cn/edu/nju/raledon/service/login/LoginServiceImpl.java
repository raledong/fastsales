package cn.edu.nju.raledon.service.login;

import cn.edu.nju.raledon.dao.UserDao;
import cn.edu.nju.raledon.model.user.User;
import cn.edu.nju.raledon.controller.login.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rale on 7/12/17.
 * 登录注册模块实现类
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserDao userDao;

    @Override
    public User login(LoginBean loginBean) {
        List<User> userList = userDao.findByIdentifier(loginBean.getIdentifier());
        for(User user : userList){
            if (user.getPassword().equals(loginBean.getPassword())) return user;
        }
        return null;
    }
}
