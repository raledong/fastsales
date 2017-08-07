package cn.edu.nju.raledon.controller.sales.form;

import java.util.List;

/**
 * Created by rale on 8/3/17.
 * 销售单上传数据
 */
public class SalesOrderForm {
    private int salesSource;

    private Long customerId;

    private List<SalesOrderItemForm> salesOrderItemFormList;

    public int getSalesSource() {
        return salesSource;
    }
    public void setSalesSource(int salesSource) {
        this.salesSource = salesSource;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<SalesOrderItemForm> getSalesOrderItemFormList() {
        return salesOrderItemFormList;
    }

    public void setSalesOrderItemFormList(List<SalesOrderItemForm> salesOrderItemFormList) {
        this.salesOrderItemFormList = salesOrderItemFormList;
    }
}
