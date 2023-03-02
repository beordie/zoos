package com.beordie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.common.AccessAnnotation;
import com.beordie.common.Result;
import com.beordie.model.entity.User;
import com.beordie.mapper.UserMapper;
import com.beordie.model.factory.UserFactory;
import com.beordie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Enumeration;
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
    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public Result<User> register(@RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        User user = UserFactory.buildUser(username, password);
        boolean save = userService.save(user);
        return save ? new Result<>() : Result.failed();
    }

    @PostMapping("login")
    public Result<User> login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        QueryWrapper<User> queryWrapper = UserFactory.buildQueryByUsername(username);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return Result.failedParam();
        }
        if (!user.getPassword().equals(password)) {
            return Result.failed();
        }
        return new Result<>();
    }

    @PostMapping("fill")
    public Result<User> fillInformation(@RequestBody User user,
                                        HttpServletRequest request) {
        Enumeration<String> username = request.getHeaders("username");
        if (username == null) {
            return Result.ACCESS;
        }
        boolean update = userService.updateById(user);
        return update ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @GetMapping("list")
    public Result<List<User>> selectUsers(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit) {
        Page queryPages = UserFactory.buildQueryPages(offset == null ? 0 : offset, limit == null ? 10 : limit);
        List<User> users = userService.page(queryPages).getRecords();
        return new Result<>(users);
    }

    @AccessAnnotation
    @GetMapping("delete")
    public Result deleteUser(@RequestParam("userId") Integer userId) {
        QueryWrapper<User> queryWrapper = UserFactory.buildQueryByUserId(userId);
        boolean remove = userService.remove(queryWrapper);
        return remove ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @GetMapping("update")
    public Result updateUser(@RequestBody User user) {
        boolean update = userService.updateById(user);
        return update ? new Result<>() : Result.failed();
    }
}
