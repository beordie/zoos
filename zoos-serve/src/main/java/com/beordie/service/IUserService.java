package com.beordie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
public interface IUserService extends IService<User> {
    User selectUser(int userId);
    List<User> selectUsers(Page queryPages);
}
