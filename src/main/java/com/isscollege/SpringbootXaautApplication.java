package com.isscollege;

import io.github.yedaxia.apidocs.Ignore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Ignore
@SpringBootApplication//标记当前类是一个springboot应用
@Controller
public class SpringbootXaautApplication {

    public static void main(String[] args) {
        System.out.println("开始...");
        SpringApplication.run(SpringbootXaautApplication.class, args);
        System.out.println("结束...");
    }
    @Ignore
    @RequestMapping("/init")
    @ResponseBody
    public String init(){
        return "hello,thymeleaf，古风建筑";
    }
    @Ignore
    @RequestMapping("/index") // 通过/index这个字符串找到index这个方法
    public String toLogin() {
        System.out.println("toLogin...");
        return "login"; //逻辑地址--视图名称 ---默认为请求转发
    }


}
