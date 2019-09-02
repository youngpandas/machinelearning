package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import pojo.sql.UserInfo;

public interface UserMapper {
	@Select("select * from UserInfo where name=#{arg0} and password=#{arg1}")
	UserInfo queryOne(String name, String password);
	@Insert("insert into UserInfo(name,password) values(#{name},#{password})")
	int save(UserInfo info);
}
