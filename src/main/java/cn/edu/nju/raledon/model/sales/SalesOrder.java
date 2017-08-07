package cn.edu.nju.raledon.model.sales;

import cn.edu.nju.raledon.controller.sales.form.SalesOrderForm;
import cn.edu.nju.raledon.controller.sales.form.SalesOrderItemForm;
import cn.edu.nju.raledon.model.Customer;
import cn.edu.nju.raledon.model.payment.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rale on 7/15/17.
 * 销售单
 */
@Entity
@Table(name="sales_order")
public class SalesOrder {

    @Id
    @Column(name = "sales_order_id")
    private Long salesOrderId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "sales_order_source")
    private SalesSource salesSource;

    /**订单创建人员**/
    @Column(name = "salesman_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sales_order_created_at")
    private Date createAt;

    /**货物是否发货**/
    @Column(name = "sales_order_issent")
    private boolean isSent;

    /**单子已经完成**/
    @Column(name = "sales_order_isfinished")
    private boolean isFinished;

    /**单子被取消**/
    @Column(name = "sales_order_iscancelled")
    private boolean isCancelled;

//    /**支付情况@Todo**/
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL)
    @OrderBy(value = "payment_created_at")
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("salesOrder")
    private List<Payment> paymentList;

    /**订单中商品列表清单**/
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "order_item_id")
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("salesOrder")
    private List<SalesOrderItem> salesOrderItems;

    public SalesOrder() {
    }

    public SalesOrder(Long salesOrderId){
        this.salesOrderId = salesOrderId;
    }

    public SalesOrder(Long salesOrderId, SalesSource salesSource, Long userId, Customer customer) {
        this.salesOrderId = salesOrderId;
        this.salesSource = salesSource;
        this.userId = userId;
        this.customer = customer;
    }

    public SalesOrder(SalesOrderForm salesOrderForm, Long salesOrderId, Long userId){
        this.salesOrderId = salesOrderId;
        this.setCustomer(new Customer(salesOrderForm.getCustomerId()));
        this.setSalesSource(salesOrderForm.getSalesSource());
        this.setSalesOrderItemsByForm(salesOrderForm.getSalesOrderItemFormList());
    }
    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public SalesSource getSalesSource() {
        return salesSource;
    }

    public void setSalesSource(SalesSource salesSource) {
        this.salesSource = salesSource;
    }

    public void setSalesSource(int salesSourceId){
        this.salesSource = SalesSource.fromCode(salesSourceId);
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public List<Payment> getPaymentList() {
        return paymentList==null ? new ArrayList<Payment>() : paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<SalesOrderItem> getSalesOrderItems() {
        return salesOrderItems==null ? new ArrayList<SalesOrderItem>() : salesOrderItems;
    }

    public void setSalesOrderItems(List<SalesOrderItem> salesOrderItems) {
        this.salesOrderItems = salesOrderItems;
    }

    public void setSalesOrderItemsByForm(List<SalesOrderItemForm> salesOrderItemFormList){
        this.salesOrderItems = new ArrayList<SalesOrderItem>();
        if(salesOrderItemFormList!=null && !salesOrderItemFormList.isEmpty()){
            for(SalesOrderItemForm salesOrderItemForm : salesOrderItemFormList){
                salesOrderItems.add(new SalesOrderItem(salesOrderItemForm));
            }
        }
    }
    public void addSalesOrderItem(SalesOrderItem salesOrderItem){
        if (this.salesOrderItems == null) this.salesOrderItems = new ArrayList<SalesOrderItem>();
        salesOrderItem.setSalesOrder(this);
        this.salesOrderItems.add(salesOrderItem);
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPayment(){
        if (paymentList==null || paymentList.isEmpty()) return 0;
        int sum = 0;
        for(Payment payment : paymentList) sum+=payment.getSum();
        return sum;
    }

    /**
     * 获得该销售单的总价
     * @return
     */
    public double getTotalPrice(){
        int sum = 0;
        for(SalesOrderItem salesOrderItem : this.getSalesOrderItems()) sum += salesOrderItem.getTotalPrice();
        return sum;
    }

    public double getNotPaid(){
        return this.getTotalPrice() - this.getTotalPayment();
    }
    /**
     * 该商品的支付是否结束
     * @return
     */
    public boolean isPaid(){
        return this.getTotalPayment() >= this.getTotalPrice();
    }
}
