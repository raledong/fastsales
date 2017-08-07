package cn.edu.nju.raledon.model;

import cn.edu.nju.raledon.controller.customer.bean.CustomerBean;
import cn.edu.nju.raledon.model.sales.SalesOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by rale on 5/11/17.
 * 客户信息
 */
@NamedQueries({
        @NamedQuery(
                name = "Customer.selectIdAndNameByKeyword",
                query = "select new map(c.customerId, c.name) " +
                        "from Customer c " +
                        "where name like :keyword " +
                        "or comment like :keyword " +
                        "order by c.name"
        ),
        @NamedQuery(
                name = "Customer.selectByKeyword",
                query = "select c from Customer c where name like :keyword or comment like :keyword order by c.name"
        )
    }
)
@Entity
@Table(name = "customer")
@DynamicInsert
public class Customer {

    /**客户编号，系统自动生成，唯一**/
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    @JsonView(CustomerBean.BriefView.class)
    private Long customerId;

    /**客户姓名**/
    @Column(name = "customer_name", nullable = false)
    @JsonView(CustomerBean.BriefView.class)
    private String name;

    /**客户手机号**/
    @Column(name = "customer_mobile")
    @JsonView(CustomerBean.DetailView.class)
    private String mobile;

    /**客户电话**/
    @Column(name = "customer_tele")
    @JsonView(CustomerBean.DetailView.class)
    private String tele;

    /**客户微信**/
    @Column(name = "customer_wechat")
    @JsonView(CustomerBean.CompleteView.class)
    private String wechat;

    /**客户的积分**/
    @Column(name = "customer_credit")
    @JsonView(CustomerBean.DetailView.class)
    private int credit;

    /**客户备注**/
    @Column(name = "customer_comment")
    @JsonView(CustomerBean.DetailView.class)
    private String comment;

    /**客户创建时间**/
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "customer_created_at")
    @JsonView(CustomerBean.DetailView.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date createdAt;

    public Customer() {
    }

    public Customer(Long customerId){
        this.setCustomerId(customerId);
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
