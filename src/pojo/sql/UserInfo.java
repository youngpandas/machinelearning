package pojo.sql;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserInfo {

	private int sid;
	@Size(min = 1,max = 20,message = "{user.name.length.error}")
	private String name;
	@NotEmpty(message = "{user.password.empty.error}")
	private String password;

	public UserInfo(int sid, @Size(min = 1, max = 20, message = "{user.name.length.error}") String name, @NotEmpty(message = "{user.password.empty.error}") String password) {
		this.sid = sid;
		this.name = name;
		this.password = password;
	}

	public UserInfo(){

	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserInfo [sid=" + sid + ", name=" + name + ", password=" + password + "]";
	}
	
}
