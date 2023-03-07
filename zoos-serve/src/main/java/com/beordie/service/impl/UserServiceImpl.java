package com.beordie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.User;
import com.beordie.mapper.UserMapper;
import com.beordie.model.factory.UserFactory;
import com.beordie.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beordie.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User selectUser(int userId) {
        QueryWrapper<User> queryWrapper = UserFactory.buildQueryByUserId(userId);
        User user = super.getOne(queryWrapper);
        user.setPassword(StringUtil.encrypt(user.getPassword()));
        return user;
    }

    @Override
    public List<User> selectUsers(Page queryPages) {
        List<User> users = super.page(queryPages).getRecords();
        users.forEach(user->{
            user.setPassword(StringUtil.encrypt(user.getPassword()));
        });
        return users;
    }
}
