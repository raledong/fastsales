package cn.edu.nju.raledon.dao;

import cn.edu.nju.raledon.model.user.User;

import java.util.List;

/**
 * Created by rale on 7/8/17.
 */
public interface UserDao extends GenericDao<User, Long> {

    /**
     * 根据用户的名字查找用户
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 根据关键词来查找用户
     * @param identifier
     * @return
     */
    List<User> findByIdentifier(String identifier);

    /**
     * 根据用户的角色查找用户
     * @param roleId
     * @return
     */
    List<User> findByRole(int roleId);
}
