package com.isscollege.controllers;

import com.github.pagehelper.PageInfo;
import com.isscollege.entity.Users;
import com.isscollege.service.impl.UserServiceImpl;
import com.isscollege.utils.ResponseResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 作者：杜丹东
 * 日期：2021/12/16 10:33
 */
/*用户模块*/
@Controller
@RequestMapping("/user")
public class UserController {
//    mvc:
//    m --model -模型 --bean   1、业务bean ---业务（service）：只进行业务处理 --持久化（dao）：只与数据库进行交互 2、javabean--进行数据的承载
//    v-View -视图 -thyemleaf ,jsp，volicity,feremaker ---只进行用户数据的收集和展示
//    c-Controller --servlet --controller --将页面收集的数据传递到后台（接受数据），进行页面的跳转（可能会伴随着数据传出）


    //1、遵循java标识符命名规则
//    2、工程名纯小写，可以采用下划线隔开
//    3、报名纯小写，遵循网址倒置的规则
//    4、类名首字母大写，遵循驼峰式命名规则，见名知意
//    5、方法名首字母小写，遵循驼峰式命名规则，见名知意
    //6、动+名字  ，比如，getAllUsers ,login,getUserInfoByUserid,modifyUserPassByUserid，removeUserByUserid,addUserInfo

    private static Logger LOG = Logger.getLogger(UserController.class); // log4j日志

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/toRegist") // 通过/index这个字符串找到index这个方法
    public String toRegist() {
        System.out.println("toLogintoRegist...");
        return "regist"; //逻辑地址--视图名称 ---默认为请求转发
    }

    @RequestMapping("/regist")
    public String regist(Users user, Map<String, Object> map) throws Exception { // 数据绑定---对象绑定

        LOG.info("用户信息为：" + user);
        try {
            int num = userService.regist(user);
            if (num > 0) {
                return "login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("registDefeat", "注册失败");
        return "regist";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Map<String, Object> map, HttpSession session)
            throws Exception {
        Users user = userService.login(username, password);
        LOG.info("....." + user);
        if (user != null && user.getUserid() > 0) {
            if("false".equals(user.getState())){
                map.put("loginDefeat", "账号已经被禁用，请与管理员联系");
                return "login";
            }
            session.setAttribute("user", user);
            List<Users> userList = userService.getAllUsers(0, 0);
            map.put("userList",userList);
            PageInfo<Users> pageInfo = new PageInfo<>(userList);
            map.put("pageInfo", pageInfo);
            return "showAllUsers";
        } else {
            map.put("loginDefeat", "用户名称或密码不正确，请重新输入");
            return "login";
        }

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();//强制使session失效
        return "login";
    }

    @RequestMapping("/modifyUserState")
    public String modifyUserState(Integer userid, Integer pageNum, Integer maxPage, String state,
                                  Map<String, Object> map) throws Exception {
        Users user = new Users();
        user.setUserid(userid);
        user.setState(state);
        userService.modifyUserState(user);
        List<Users> userList = userService.getAllUsers(pageNum, maxPage);
        map.put("userList", userList);
        PageInfo<Users> pageInfo = new PageInfo<>(userList);
        map.put("pageInfo", pageInfo);

        return "showAllUsers";
    }

    @RequestMapping("/getAllUsers")
    public String getAllUsers(Integer pageNum, Integer maxPage, Map<String, Object> map) throws Exception {

        List<Users> userList = userService.getAllUsers(pageNum, maxPage);
        PageInfo<Users> pageInfo = new PageInfo<>(userList);
        map.put("pageInfo", pageInfo);
        map.put("userList", userList);
        return "showAllUsers";
    }

    /**
     * 获取所有用户信息
     * @author:杜丹东 D.D.D  2021/12/16 15:40
     * @Return com.isscollege.utils.ResponseResult<java.util.List<com.isscollege.entity.Users>>
     **/
    @RequestMapping("/getAllUsers2")
    @ResponseBody
    public  ResponseResult<List<Users>> getAllUsers2() throws Exception {
        List<Users> userList = userService.getAllUsers(0, 0);
        ResponseResult<List<Users>> result = new ResponseResult<>();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(userList);
        return result;
    }

    /**
     * 用户登录
     * @author:杜丹东 D.D.D  2021/12/16 15:41
     * @param username
     * @param password
     * @Return com.isscollege.utils.ResponseResult<java.util.List<com.isscollege.entity.Users>>
     **/
//    @RequestMapping("/login2")
    @PostMapping("login2")
    @ResponseBody
    public ResponseResult<List<Users>> login2(@RequestParam String username, String password, Map<String, Object> map, HttpSession session)
            throws Exception {
        Users user = userService.login(username, password);
        ResponseResult<List<Users>> result = new ResponseResult<>();
        if (user != null && user.getUserid() > 0) {
            if("false".equals(user.getState())){
                result.setCode(400);
                result.setMsg("失败");
                result.setData(null);
                return result;
            }
            session.setAttribute("user", user);
            List<Users> userList = userService.getAllUsers(0, 0);
            result.setCode(200);
            result.setMsg("成功");
            result.setData(userList);
            return result;
        } else {
            result.setCode(400);
            result.setMsg("失败");
            result.setData(null);
            return result;
        }

    }




}
