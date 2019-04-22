package com.qf.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/17 17:40
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null && user.getPassword().equals(MD5Utils.toMD5(password))) {
            return user;
        }
        return null;
    }

    @Override
    public int register(User user) {
        user.setPassword(MD5Utils.toMD5(user.getPassword()));
        userMapper.insert(user);
        return 1;
    }

    @Override
    public int jihuoUser(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        user.setStatus(1);
        userMapper.updateById(user);
        return 1;
    }

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User getUserById(int uid) {
        return userMapper.selectById(uid);
    }
}
