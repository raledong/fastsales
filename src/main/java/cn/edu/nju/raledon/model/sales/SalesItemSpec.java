package cn.edu.nju.raledon.model.sales;

import cn.edu.nju.raledon.controller.sales.bean.SalesItemSpecBean;
import cn.edu.nju.raledon.model.product.ProductSpec;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rale on 7/15/17.
 * 销售单品中的商品详情，一旦销售出去，就减少对应ProductSpec的库存
 */
@Entity
@Table(name="sales_item_spec")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "salesItemSpecId")
public class SalesItemSpec implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "item_spec_id")
    private Long salesItemSpecId;

    @ManyToOne
    @JoinColumn(name = "product_spec_id")
    private ProductSpec productSpec;

    @Column(name = "item_spec_quantity")
    private int quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_item_id")
    @JsonIgnoreProperties("salesItemSpecList")
    private SalesItem salesItem;

    public SalesItemSpec() {
    }

    public SalesItemSpec(Long productSpecId, int quantity) {
        this.productSpec = new ProductSpec(productSpecId);
        this.quantity = quantity;
    }

    public SalesItemSpec(SalesItemSpecBean salesItemSpecBean){
        this(salesItemSpecBean.getProductSpecId(), salesItemSpecBean.getQuantity());
    }

    public Long getSalesItemSpecId() {
        return salesItemSpecId;
    }

    public void setSalesItemSpecId(Long salesItemSpecId) {
        this.salesItemSpecId = salesItemSpecId;
    }

    public ProductSpec getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(ProductSpec productSpec) {
        this.productSpec = productSpec;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSalesItem(SalesItem salesItem) {
        this.salesItem = salesItem;
    }


    public SalesItem getSalesItem() {
        return salesItem;
    }

    //判断该件单品是否还有剩余
    public boolean isAvailable(){
        return quantity<=this.getProductSpec().getQuantity();
    }

    public String getProductName(){
        return this.getProductSpec().getProductName();
    }

    public String getProductSpecDetail(){
        return this.getProductSpec().getDetail();
    }
    public String getBriefInfo(){
        return "[" + this.getProductName() + " " + this.getProductSpecDetail() + " " + this.getQuantity() + "件] ";
    }
}
