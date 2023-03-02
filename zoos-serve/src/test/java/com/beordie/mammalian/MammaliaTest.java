package com.beordie.mammalian;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beordie.model.entity.User;
import com.beordie.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mao
 * @createTime 2023/3/1 16:40
 * @Description: 测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MammaliaTest {

    // @Autowired
    @Resource
    private UserMapper userMapper;

    // 测试查询
    @Test
    public void testQuery(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 查询指定姓名用户
    @Test
    public void testUsername() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username","毛文星");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    // 测试分页查询
    @Test
    public void testPage(){
        Page<User> page = new Page<>(2,1);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }
}

