package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.UserMapper;
import pojo.sql.UserInfo;
import service.UserService;
@Service("userservice")
public class UserServiceImpl implements UserService{
	@Autowired
	 UserMapper UserMapper;
	@Override
	public UserInfo queryOne(String name, String password) {
		// TODO Auto-generated method stub
		return UserMapper.queryOne(name, password);
	}

	@Override
	public int save(UserInfo info) {
		// TODO Auto-generated method stub
		return UserMapper.save(info);
	}
}
