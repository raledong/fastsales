package cn.edu.nju.raledon.controller.sales.bean;

import cn.edu.nju.raledon.model.product.ProductSpec;
import cn.edu.nju.raledon.model.sales.SalesItem;

import javax.persistence.*;

/**
 * Created by rale on 7/16/17.
 * 上架商品详细信息VO
 */
public class SalesItemSpecBean {

    private Long productSpecId;

    private int quantity;


    public Long getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
