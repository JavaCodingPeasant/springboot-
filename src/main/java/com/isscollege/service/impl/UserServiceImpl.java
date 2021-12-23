package com.isscollege.service.impl;

import com.github.pagehelper.PageHelper;
import com.isscollege.dao.IUserDao;
import com.isscollege.entity.Users;
import com.isscollege.service.IUserService;
import com.isscollege.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者: 杜丹东 D.D.D 日期: 2021年7月5日下午4:27:05
 */
@Service // 标记当前类为一个和业务类(sprin组件，默认是单例模式)---<bean
			// id=com.isscolleg.service.impl.UserServiceImpl></bean>
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao; // 依赖注入

	@Override
	public int regist(Users user) throws Exception {
		String newPass = new MD5().getMD5ofStr(user.getPassword());
		user.setPassword(newPass);
		return userDao.regist(user);
	}

	@Override
	public int removeUserByUserid(Integer userid) throws Exception {

		return userDao.removeUserByUserid(userid);
	}

	@Override
	public int modifyUserByUserid(Users user) throws Exception {
//		String newPass = new MD5().getMD5ofStr("88888888");
//		user.setPassword(newPass);
		return userDao.modifyUserByUserid(user);
	}

	@Override
	public Users login(String username, String password) throws Exception {

		String newPass = new MD5().getMD5ofStr(password);
//
		return userDao.login(username, newPass);

	}

	@Override
	public List<Users> getAllUsers(Integer pageNum, Integer maxPage) throws Exception {
		System.out.println(pageNum + "---" + maxPage);
		if (pageNum == null) {
			pageNum = 1;
		} else if (pageNum <= 0) {
			pageNum = 1;
		} else if (maxPage != null ) {
			if (pageNum >= maxPage) {
				pageNum = maxPage;
			}
		}
//		 PageHelper.startPage(第几页, 每页显示几条数据);
//		 mysql：limit ,只能对紧邻（下）的一条sql语句起作 用
		PageHelper.startPage(pageNum, 3);
		return userDao.getAllUsers();
	}


	@Override
	public int modifyUserState(Users user) throws Exception {
		if ("true".equals(user.getState())) {
			user.setState("false");
		} else {
			user.setState("true");
		}
		return userDao.modifyUserState(user);
	}


}
