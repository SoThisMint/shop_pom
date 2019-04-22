package com.qf.service;

import com.qf.entity.User;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/17 17:30
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IUserService {

    User login(String username, String password);

    int register(User user);

    int jihuoUser(String username);

    User getUserByUsername(String uname);

    User getUserById(int uid);
}
