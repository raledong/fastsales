package cn.edu.nju.raledon.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by rale on 4/28/17.
 * 使用系统的用户
 */
@Entity
@Table(name = "sysuser")
@FilterDef(name = "userRoleFilter", parameters = { @ParamDef(name = "userRole", type = "integer") })
@Filters({ @Filter(name = "userRoleFilter", condition = ":userRole = user_role") })

@NamedQueries({
        @NamedQuery(
                name = "User.findByMultipleIdentifier",
                query = "select u from User u where u.name = :identifier or u.mobile = :identifier"
        )
})
public class User {

    public User(Long userId) {
        this.userId = userId;
    }

    public User(){}

    public User(String password, String name, String mobile, String tele, UserRole userRole){
        this(password, name, mobile, tele, userRole.getRoleId());
    }

    public User(String password, String name, String mobile, String tele, int roleId){
        this.password = password;
        this.name = name;
        this.mobile = mobile;
        this.tele = tele;
        this.roleId = roleId;
    }

    @GeneratedValue
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name="user_password", nullable = false)
    @JsonIgnore
    private String password;

    @NaturalId
    @Column(name="user_name", nullable = false)
    private String name;

    @Column(name="user_mobile")
    private String mobile;

    @Column(name="user_tele")
    private String tele;

    @Column(name="user_role")
    private int roleId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "user_created_at")
    private Date createdAt;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public int getRole() {
        return roleId;
    }

    public void setRole(int role) {
        this.roleId = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
