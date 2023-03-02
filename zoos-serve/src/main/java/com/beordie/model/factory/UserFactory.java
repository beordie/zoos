package com.beordie.model.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.User;

import java.time.LocalDateTime;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/2 8:28
 * @User beordie
 */
public class UserFactory {
    public static User buildUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCtime(LocalDateTime.now());
        return user;
    }

    public static QueryWrapper<User> buildQueryByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return wrapper;
    }

    public static QueryWrapper<User> buildQueryByUserId(int userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",userId);
        return wrapper;
    }

    public static Page buildQueryPages(int offset, int limit) {
        Page<User> page = new Page<>(offset,limit);
        return page;
    }
}
