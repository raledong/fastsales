package cn.edu.nju.raledon.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rale on 4/30/17.
 * 供应商信息
 */
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue
    @Column(name = "supplier_id")
    private Long supplierId;

    //供应商名称，一般填厂家名称
    @NaturalId
    @Column(name = "supplier_name", nullable = false, unique = true)
    private String name;

    @Column(name = "supplier_mobile")
    private String mobile;

    @Column(name = "supplier_tele")
    private String tele;

    @Column(name = "supplier_wechat")
    private String wechat;

    @Column(name = "supplier_agent_name")
    private String agentName;

    @Column(name = "supplier_comment")
    private String comment;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "supplier_created_at")
    private Date createdAt;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long producerId) {
        this.supplierId = producerId;
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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
