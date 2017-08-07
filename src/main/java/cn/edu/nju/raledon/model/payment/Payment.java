package cn.edu.nju.raledon.model.payment;

import cn.edu.nju.raledon.model.sales.SalesOrder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rale on 7/10/17.
 * 收入和支出
 */
@Entity
@Table(name = "payment")
public class Payment {

    /**付款码，系统生成**/
    @Id
    @Column(name = "payment_id")
    private Long paymentId;

    /**总金额**/
    @Column(name = "payment_sum")
    private double sum;

    /**类型**/
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "payment_created_at")
    private Date createdAt;

    @Column(name = "payment_comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;

    public Payment(){

    }

    public Payment(Long paymentId, double sum, Long salesOrderId, PaymentType paymentType, String comment){
        this.setPaymentId(paymentId);
        this.setSum(sum);
        this.setSalesOrder(new SalesOrder(salesOrderId));
        this.setPaymentType(paymentType);
        this.setComment(comment);
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentType(int code){
        this.setPaymentType(PaymentType.fromCode(code));
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }
}
