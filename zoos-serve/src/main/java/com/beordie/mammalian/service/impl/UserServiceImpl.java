package com.beordie.mammalian.service.impl;

import com.beordie.mammalian.entity.User;
import com.beordie.mammalian.mapper.UserMapper;
import com.beordie.mammalian.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
