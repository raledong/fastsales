package cn.edu.nju.raledon.controller.product.bean;

import cn.edu.nju.raledon.model.product.ProductSpec;

/**
 * Created by rale on 7/12/17.
 * 商品详细规格VO
 */
public class ProductSpecBean {

    public ProductSpecBean() {
    }

    public ProductSpecBean(String properties, long quantity, int unit) {
        this.properties = properties;
        this.quantity = quantity;
        this.unit = unit;
    }

    public ProductSpecBean(Long productSpecId, String properties, long quantity, int unit) {
        this.productSpecId = productSpecId;
        this.properties = properties;
        this.quantity = quantity;
        this.unit = unit;
    }

    public ProductSpecBean(ProductSpec productSpec){
        this(productSpec.getProductSpecId(), productSpec.getDetail(), productSpec.getQuantity(), 1);
    }

    private Long productSpecId;

    private String properties;

    private long quantity;

    private int unit;

    public Long getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public long getRealQuantity(){
        return this.getQuantity() * this.getUnit();
    }
}
