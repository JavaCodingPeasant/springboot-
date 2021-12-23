package com.isscollege.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 作者：D.D.D
 * 日期：2021/11/15 8:41
 */
@Data
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
}
