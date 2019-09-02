package pojo;

import pojo.sql.UserInfo;

/**
 * @program: MachineLearning
 * @description: 模拟测试数据集
 * @author: Mr.Sun
 * @create: 2019-05-22 17:15
 **/

public class MockUser {
    private int code ;
    private String msg;
    private UserInfo user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
