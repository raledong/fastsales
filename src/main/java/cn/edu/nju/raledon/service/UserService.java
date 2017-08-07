package cn.edu.nju.raledon.service;


import cn.edu.nju.raledon.model.user.User;

import java.util.List;

/**
 * Created by mark on 4/24/15.
 */
public interface UserService {
    public void saveUsers(List<User> us);
    public List<User> getAllUsernames();
}
