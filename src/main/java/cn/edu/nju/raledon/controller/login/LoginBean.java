package cn.edu.nju.raledon.controller.login;

/**
 * Created by rale on 5/8/17.
 * 登录VO
 */
public class LoginBean {
    /**用户名**/
    private String identifier;
    /**密码**/
    private String password;

    public LoginBean() {
    }
    public LoginBean(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid(){
        if(identifier==null || identifier.equals("")) return false;
        if(password==null || password.equals("")) return false;
        return true;
    }
}
