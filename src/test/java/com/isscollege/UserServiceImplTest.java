package com.isscollege;

import com.isscollege.entity.Users;
import com.isscollege.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 作者：杜丹东
 * 日期：2021/12/16 14:37
 */

// 类名后加Test,代表测试哪个类
@RunWith(SpringRunner.class) // 运行Spring环境
@SpringBootTest // 标记当前类为一个springboot的单元测试类，依赖spring-boot-starter-test
@WebAppConfiguration // 单元测试对web的支持（1.当前类不能是final修饰的类
// 2.当前类不能是匿名内部类）
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Before //在目标方法执行之前执行---资源的初始化
    public void before(){
        System.out.println("before....");
    }
    @Test //目标方法
    public void test(){
        System.out.println("真正测试的方法");
    }

    @Test
    public void testLogin_correct_username_pass_except_pass() throws Exception {//场景1
        Users user = userService.login("admin", "admin");
        System.out.println(user);
        Assert.assertNotEquals(null,user);
    }

    @Test
    public void testLogin_correct_username_error_pass_except_notpass() throws Exception { //场景2
        Users user = userService.login("admin", "asfdasdf");
//        Assert.assertEquals(null,user);
        Assert.assertEquals(null,user); //断言
    }

    @Test
    public void testLogin_error_username_error_pass_except_notpass() throws Exception { //场景3
        Users user = userService.login("asdfaew", "asfdasdf");
        Assert.assertEquals(null,user);
    }

    @Test
    public void testLogin_null_username_correct_pass_except_notpass() throws Exception { //场景4
        Users user = userService.login("asdfaew", "asfdasdf");
        Assert.assertEquals(null,user);
    }

    @Test
    public void testLogin_null_username_null_pass_except_notpass() throws Exception { //场景5
        Users user = userService.login("asdfaew", "asfdasdf");
        Assert.assertEquals(null,user);
    }

    //对象 ---空
    //对象（字符串） ---空，正确与否 ，字段越多，场景越多
    //对象（数字） 正确性，边界值





    @After //在目标方法执行之后执行--资源回收
    public void after(){
        System.out.println("after....");
    }
}
