package cn.edu.nju.raledon.controller.sales.bean;

import cn.edu.nju.raledon.model.sales.SalesItem;

import javax.persistence.Column;
import java.util.List;

/**
 * Created by rale on 7/16/17.
 * 上架单品VO
 */
public class SalesItemBean {

    private Long salesItemId;

    private String serializedId;

    private String name;

    private double defaultPrice;

    private List<SalesItemSpecBean> salesItemSpecBeanList;

    public SalesItemBean() {
    }
    public SalesItemBean(SalesItem salesItem) {
    }
    public String getSerializedId() {
        return serializedId;
    }

    public void setSerializedId(String serializedId) {
        this.serializedId = serializedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public List<SalesItemSpecBean> getSalesItemSpecBeanList() {
        return salesItemSpecBeanList;
    }

    public void setSalesItemSpecBeanList(List<SalesItemSpecBean> salesItemSpecBeanList) {
        this.salesItemSpecBeanList = salesItemSpecBeanList;
    }
}
