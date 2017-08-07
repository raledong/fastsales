package cn.edu.nju.raledon.controller.login;

/**
 * Created by rale on 5/8/17.
 */
public enum LoginMessage {

    WRONG_PASSWORD("密码错误"),
    USER_NOT_EXISTS("该用户不存在"),
    INPUT_NOT_EMPTY("输入不能为空"),
    INVALID_CREDENTIAL("登录失败"),
    LOGIN_SUCCESS("登录成功");

    private String info;

    LoginMessage(String info){
        this.info = info;
    }

    public String getInfo(){
        return info;
    }
}
