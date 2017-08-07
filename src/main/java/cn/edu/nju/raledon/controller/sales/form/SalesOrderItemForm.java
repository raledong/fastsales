package cn.edu.nju.raledon.controller.sales.form;

/**
 * Created by rale on 8/3/17.
 * 销售单 单品 表单
 */
public class SalesOrderItemForm {
    private Long salesOrderItemId;

    private int quantity;

    private double salesPrice;

    public Long getSalesOrderItemId() {
        return salesOrderItemId;
    }

    public void setSalesOrderItemId(Long salesOrderItemId) {
        this.salesOrderItemId = salesOrderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }
}
