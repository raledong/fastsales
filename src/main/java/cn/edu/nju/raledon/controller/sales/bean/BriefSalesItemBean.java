package cn.edu.nju.raledon.controller.sales.bean;

import cn.edu.nju.raledon.model.sales.SalesItem;
import cn.edu.nju.raledon.model.sales.SalesItemSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 7/21/17.
 * 销售单品的简单信息
 */
public class BriefSalesItemBean {

    private Long salesItemId;

    private String name;

    private String available;

    private double price;

    private List<String> salesItemSpecsInfo;

    public BriefSalesItemBean() {
    }
    public BriefSalesItemBean(Long salesItemId, String name, double price, String isAvailable) {
        this.setSalesItemId(salesItemId);
        this.setName(name);
        this.setPrice(price);
        this.available = isAvailable;
    }

    public BriefSalesItemBean(SalesItem salesItem){
        this(salesItem.getSalesItemId(), salesItem.getName(), salesItem.getDefaultPrice(), salesItem.isAvailable()?"是":"否");
        this.setSalesItemSpecsInfoBySalesItemSpecs(salesItem.getSalesItemSpecList());
    }

    public Long getSalesItemId() {
        return salesItemId;
    }

    public void setSalesItemId(Long salesItemId) {
        this.salesItemId = salesItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getSalesItemSpecsInfo() {
        if (this.salesItemSpecsInfo==null) salesItemSpecsInfo = new ArrayList<String>();
        return salesItemSpecsInfo;
    }

    public void setSalesItemSpecsInfo(List<String> salesItemSpecsInfo) {
        this.salesItemSpecsInfo = salesItemSpecsInfo;
    }

    public void setSalesItemSpecsInfoBySalesItemSpecs(List<SalesItemSpec> salesItemSpecs){
        for(SalesItemSpec s : salesItemSpecs){
            this.getSalesItemSpecsInfo().add(s.getBriefInfo());
        }
    }
}
