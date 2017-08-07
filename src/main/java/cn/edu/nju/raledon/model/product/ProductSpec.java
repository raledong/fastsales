package cn.edu.nju.raledon.model.product;

import cn.edu.nju.raledon.controller.product.bean.ProductSpecBean;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

/**
 * Created by rale on 7/9/17.
 * 在库存中的商品详情，包括多种属性如颜色图案等
 */
@Entity
@Table(name = "product_spec")
public class ProductSpec {

    public ProductSpec(){

    }

    public ProductSpec(Long productSpecId){
        this.productSpecId = productSpecId;
    }
    public ProductSpec(ProductSpecBean productSpecBean){
        this(productSpecBean.getRealQuantity(), productSpecBean.getProperties(), null);
    }

    public ProductSpec(long quantity, String detail, Product product) {
        this.quantity = quantity;
        this.detail = detail;
        this.product = product;
    }

    @Id
    @GeneratedValue
    @Column(name = "product_spec_id")
    private Long productSpecId;

    @Column(name = "product_spec_quantity")
    private long quantity;

    @ColumnDefault(value = "无")
    @Column(name = "product_spec_detail", nullable = false)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("productSpecList")
    private Product product;

    public Long getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductName(){
        return this.getProduct().getName();
    }
}
