package cn.edu.nju.raledon.model.sales;

import cn.edu.nju.raledon.controller.sales.form.SalesOrderForm;
import cn.edu.nju.raledon.controller.sales.form.SalesOrderItemForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by rale on 7/15/17.
 * 销售清单中的单品和数量
 */
@Entity
@Table(name = "sales_order_item")
public class SalesOrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long salesOrderItemId;

    @ManyToOne
    @JoinColumn(name = "sales_item_id")
    private SalesItem salesItem;

    @Column(name = "order_item_quantity")
    private int quantity;

    @Column(name = "order_item_price")
    private double salesPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("salesOrderItems")
    private SalesOrder salesOrder;

    public SalesOrderItem() {
    }

    public SalesOrderItem(Long salesOrderItemId){
        this.salesOrderItemId = salesOrderItemId;
    }

    public SalesOrderItem(Long salesItemId, int quantity, double salesPrice) {
        this.salesItem = new SalesItem(salesItemId);
        this.quantity = quantity;
        this.salesPrice = salesPrice;
    }

    /**
     * SalesOrderItemForm to SalesOrderItem
     * @param salesOrderItemForm
     */
    public SalesOrderItem(SalesOrderItemForm salesOrderItemForm){
        this(salesOrderItemForm.getSalesOrderItemId(), salesOrderItemForm.getQuantity(), salesOrderItemForm.getSalesPrice());
    }

    public Long getSalesOrderItemId() {
        return salesOrderItemId;
    }

    public void setSalesOrderItemId(Long salesOrderItemId) {
        this.salesOrderItemId = salesOrderItemId;
    }

    public SalesItem getSalesItem() {
        return salesItem;
    }

    public void setSalesItem(SalesItem salesItem) {
        this.salesItem = salesItem;
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

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    /**
     * 返回销售单上单件商品的总价
     * 总价 = 数量 * 售价
     * @return
     */
    public double getTotalPrice(){
        return this.getQuantity() * this.getSalesPrice();
    }
}
