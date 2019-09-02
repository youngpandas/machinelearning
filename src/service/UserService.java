package service;

import pojo.sql.UserInfo;

public interface UserService {
	
	UserInfo queryOne(String name, String password);
	int save(UserInfo info);
}
