package com.beordie.mammalian.controller;

import com.beordie.mammalian.entity.User;
import com.beordie.mammalian.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author coffeemao
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/mammalian/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/hello")
    public List<User> hello(){
        return userMapper.selectList(null);
    }
}
