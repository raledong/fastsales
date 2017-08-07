package cn.edu.nju.raledon.model.purchase;

import cn.edu.nju.raledon.model.Supplier;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 7/10/17.
 * 进货单实体类
 */
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @Column(name = "purchase_order_id")
    private Long purchaseOrderId;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "purchase_order_real_id")
    private String realId;

    @Column(name = "purchase_order_sum")
    private double sum;

    @Column(name = "purchase_order_comment")
    private String comment;



    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "product_spec_id")
    @Fetch(FetchMode.JOIN)
    private List<PurchaseOrderItem> purchaseOrderItemList;

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPurchaseOrderItemsCount(){
        return purchaseOrderItemList==null ? 0 : purchaseOrderItemList.size();
    }

    public List<PurchaseOrderItem> getPurchaseOrderItemList() {
        return purchaseOrderItemList==null ? new ArrayList<PurchaseOrderItem>() : this.purchaseOrderItemList;
    }

    public void setPurchaseOrderItemList(List<PurchaseOrderItem> purchaseOrderItemList) {
        this.purchaseOrderItemList = purchaseOrderItemList;
    }

    public void addPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem){
        if (purchaseOrderItemList == null){
            purchaseOrderItemList = new ArrayList<PurchaseOrderItem>();
        }
        purchaseOrderItem.setPurchaseOrder(this);
        purchaseOrderItemList.add(purchaseOrderItem);
    }

    public void removePurchaseOrderItemAt(int index){
        if(purchaseOrderItemList==null || index>=getPurchaseOrderItemsCount()){
            //throw exception
        }
        purchaseOrderItemList.remove(index);
    }
}
