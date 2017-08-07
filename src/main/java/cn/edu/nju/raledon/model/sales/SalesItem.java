package cn.edu.nju.raledon.model.sales;

import cn.edu.nju.raledon.controller.sales.bean.SalesItemBean;
import cn.edu.nju.raledon.controller.sales.bean.SalesItemSpecBean;
import cn.edu.nju.raledon.model.product.ProductSpec;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rale on 7/15/17.
 * 销售单品，单品中可以包含多样SalesItemSpec，相当于打包销售
 */
@NamedQueries({
        @NamedQuery(name = "SalesItem.searchSalesItemBySerializedId",
                query = "select s from SalesItem s where s.serializedId = :serializedId"
        ),
        @NamedQuery(name = "SalesItem.searchSalesItemByKeyword",
                query = "select s from SalesItem s where s.serializedId like :keyword " +
                        "or s.name like :keyword " +
                        "order by s.name"
        )
    }
)
@Entity
@Table(name = "sales_item")
public class SalesItem implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "sales_item_id")
    private Long salesItemId;

    @NaturalId
    @Column(name = "sales_item_serialized_id", nullable = false, unique = true)
    private String serializedId;

    @Column(name = "sales_item_name", nullable = false, unique = true)
    private String name;

    @Column(name = "sales_item_default_price")
    private double defaultPrice;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "sales_item_create_at")
    private Date createdAt;

    @OneToMany(mappedBy = "salesItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "item_spec_id")
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("salesItem")
    private List<SalesItemSpec> salesItemSpecList;

    public SalesItem() {
    }

    public SalesItem(String serializedId, String name, double defaultPrice) {
        this.serializedId = serializedId;
        this.name = name;
        this.defaultPrice = defaultPrice;
    }

    public SalesItem(SalesItemBean salesItemBean){
        this(salesItemBean.getSerializedId(), salesItemBean.getName(), salesItemBean.getDefaultPrice());
        if(salesItemBean.getSalesItemSpecBeanList() != null){
            for (SalesItemSpecBean salesItemSpecBean : salesItemBean.getSalesItemSpecBeanList()){
                this.addSalesItemSpec(new SalesItemSpec(salesItemSpecBean));
            }
        }
    }

    public SalesItem(Long salesItemId){
        this.salesItemId = salesItemId;
    }

    public void setSalesItemId(Long salesItemId) {
        this.salesItemId = salesItemId;
    }

    public void setSerializedId(String serializedId) {
        this.serializedId = serializedId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setSalesItemSpecList(List<SalesItemSpec> salesItemSpecList) {
        this.salesItemSpecList = salesItemSpecList;
    }

    public Long getSalesItemId() {
        return salesItemId;
    }

    public String getSerializedId() {
        return serializedId;
    }

    public String getName() {
        return name;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<SalesItemSpec> getSalesItemSpecList() {
        return salesItemSpecList==null ? new ArrayList<SalesItemSpec>() : salesItemSpecList;
    }

    public void addSalesItemSpec(SalesItemSpec salesItemSpec){
        if (salesItemSpecList==null) salesItemSpecList = new ArrayList<SalesItemSpec>();
        salesItemSpec.setSalesItem(this);
        salesItemSpecList.add(salesItemSpec);
    }

    //该销售单品中的商品是否还有足够的剩余
    public boolean isAvailable(){
        for(SalesItemSpec salesItemSpec : this.getSalesItemSpecList()){
            if (!salesItemSpec.isAvailable()) return false;
        }
        return true;
    }

    public String getSalesItemSpecsBriefInfo(){
        String info = "";
        for(SalesItemSpec salesItemSpec : this.getSalesItemSpecList()){
            info += salesItemSpec.getBriefInfo();
        }
        return info.isEmpty() ? "无" : info;
    }

}
