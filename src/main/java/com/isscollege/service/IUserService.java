package com.isscollege.service;

import com.isscollege.entity.Users;

import java.util.List;

/**
 * 作者: 杜丹东 D.D.D 日期: 2021年7月5日下午4:25:46
 */
public interface IUserService {

	int regist(Users user) throws Exception;

	int removeUserByUserid(Integer userid) throws Exception;

	int modifyUserByUserid(Users user) throws Exception;

	Users login(String username, String password) throws Exception;

	List<Users> getAllUsers(Integer pageNum, Integer maxPage) throws Exception;

	public int modifyUserState(Users user) throws Exception;
}
