package com.beordie.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.common.AccessAnnotation;
import com.beordie.common.Result;
import com.beordie.model.entity.User;
import com.beordie.mapper.UserMapper;
import com.beordie.model.factory.UserFactory;
import com.beordie.model.request.AdminUser;
import com.beordie.model.request.Sign;
import com.beordie.service.IEmailService;
import com.beordie.service.IUserService;
import com.beordie.service.impl.HtmlEmailService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired(required = false)
    private IEmailService htmlEmailService;

    @PostMapping("register")
    public Result<User> register(@RequestBody Sign sign) {
        User user = UserFactory.buildUser(sign.getUsername(), sign.getPassword());
        boolean save = userService.save(user);
        return save ? new Result<>() : Result.failed();
    }

    @PostMapping("login")
    public Result<User> login(@RequestBody Sign sign) {
        QueryWrapper<User> queryWrapper = UserFactory.buildQueryByUsername(sign.getUsername());
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return Result.failedParam();
        }
        if (!user.getPassword().equals(sign.getPassword())) {
            return Result.failed();
        }
        return new Result<>(user);
    }

    @GetMapping("login/code")
    public Result<String> ceateCode(@RequestParam("email") String email) {
        String code = htmlEmailService.sendCode(email);
        return new Result<>(code);
    }

    @PostMapping("login/admin")
    public Result<User> loginAdmin(@RequestBody AdminUser adminUser) {
        if (!"beordie".equals(adminUser.getUsername()) || !"123456".equals(adminUser.getPassword())) {
            return Result.PARAM;
        }
        String oldCode = htmlEmailService.getCode(adminUser.getEmail());
        if (oldCode == null || !oldCode.equals(adminUser.getCode())) {
            return Result.PARAM;
        }
        return new Result<>(UserFactory.buildUser(adminUser.getUsername(), ""));
    }

    @PostMapping("fill")
    public Result<User> fillInformation(@RequestBody User user,
                                        HttpServletRequest request) {
        String username = request.getHeader("username");
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
        List<User> users = userService.selectUsers(queryPages);
        return new Result<>(users);
    }

    @GetMapping("select/{userId}")
    public Result<User> selectUser(@PathVariable Integer userId) {
        User user = userService.selectUser(userId);
        return new Result<>(user);
    }

    @AccessAnnotation
    @GetMapping("count")
    public Result<Long> selectUsersTotal() {
        long count = userService.count();
        return new Result<>(count);
    }

    @AccessAnnotation
    @PostMapping("delete")
    public Result deleteUser(@RequestParam("userId") Integer userId) {
        QueryWrapper<User> queryWrapper = UserFactory.buildQueryByUserId(userId);
        boolean remove = userService.remove(queryWrapper);
        return remove ? new Result<>() : Result.failed();
    }

    @AccessAnnotation
    @PostMapping("update")
    public Result updateUser(@RequestBody User user) {
        boolean update = userService.updateById(user);
        return update ? new Result<>() : Result.failed();
    }
}
