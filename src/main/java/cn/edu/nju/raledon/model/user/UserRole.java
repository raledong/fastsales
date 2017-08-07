package cn.edu.nju.raledon.model.user;

/**
 * Created by rale on 7/7/17.
 * 用户角色
 */
public class UserRole{
    public static final int MANAGER = 1109;
    public static final int SALESMAN = 1101;
    public static final int ADMIN = 1100;
    public static final int PRODUCT_MANAGER = 1102;

    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
