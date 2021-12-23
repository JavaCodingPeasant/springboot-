package com.isscollege.dao;

import com.isscollege.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 作者: 杜丹东 D.D.D 日期: 2021年7月5日下午4:27:54
 */
@Mapper // 标记当前类为一个功能映射类（组件）
public interface IUserDao {
	@Insert("insert into users (username,password,sex) values(#{username},#{password},#{sex})")
	public int regist(Users user) throws Exception;

	public int removeUserByUserid(Integer userid) throws Exception;

	@Update("update users set password=#{password} where userid=#{userid}")
	public int modifyUserByUserid(Users user) throws Exception;

	@Select("select * from users where username=#{username} and password=#{password}")
	public Users login(@Param("username") String username, @Param("password") String password) throws Exception;

	@Select("select * from users")
	public List<Users> getAllUsers() throws Exception;

	@Update("update users set state=#{state} where userid=#{userid}")
	public int modifyUserState(Users user) throws Exception;

}
