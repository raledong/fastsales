package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.UserDao;
import cn.edu.nju.raledon.model.user.User;
import org.hibernate.Filter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/8/17.
 * UserDao实现类
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByName(String name) {
        return (User) this.currentSession().bySimpleNaturalId(User.class).load(name);
    }

    @Override
    public List<User> findByIdentifier(String identifier) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("identifier", identifier);
        return this.findByNamedQueryAndParams("User.findByMultipleIdentifier", map);
    }

    @Override
    public List<User> findByRole(int roleId) {
        Filter filter = this.currentSession().enableFilter("userRoleFilter");
        filter.setParameter("userRole", roleId);
        List<User> userList = findAll();
        this.currentSession().disableFilter("userRoleFilter");
        return userList;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println(userDao.findByRole(1));
    }
}
