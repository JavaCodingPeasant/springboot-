package com.isscollege.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 作者：杜丹东
 * 日期：2021/12/16 10:41
 */
@Data
public class Users implements Serializable {
    /*用户编号*/
    private Integer userid;
    /*用户名称*/
    private String username;
    /*用户密码*/
    private String password;
    /*用户性别*/
    private String sex;
    /*账号状态*/
    private String state;



}
