package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.PurchaseOrderDao;
import cn.edu.nju.raledon.model.product.ProductSpec;
import cn.edu.nju.raledon.model.purchase.PurchaseOrder;
import cn.edu.nju.raledon.model.purchase.PurchaseOrderItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 7/10/17.
 * PurchaseOrderDao实现类
 */
@Repository
public class PurchaseOrderDaoImpl extends GenericDaoImpl<PurchaseOrder, Long> implements PurchaseOrderDao {

    public PurchaseOrderDaoImpl() {
        super(PurchaseOrder.class);
    }


    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        PurchaseOrderDao purchaseOrderDao = context.getBean(PurchaseOrderDao.class);
//        PurchaseOrder purchaseOrder = new PurchaseOrder();
//        purchaseOrder.setPurchaseOrderId(1L);
//        purchaseOrder.setRealId("123-4");
//        purchaseOrder.setSum(100);
//        Supplier supplier = new Supplier();
//        supplier.setSupplierId(1L);
//        purchaseOrder.setSupplier(supplier);
//
//        ProductSpec productSpec = new ProductSpec();
//        productSpec.setProductSpecId(1L);
//        PurchaseOrderItem purchaseOrderItem1 = new PurchaseOrderItem();
//        purchaseOrderItem1.setProductSpec(productSpec);
//        purchaseOrderItem1.setQuantity(100);
//
//        purchaseOrder.addPurchaseOrderItem(purchaseOrderItem1);
//
//
//        purchaseOrderDao.add(purchaseOrder);

        PurchaseOrder purchaseOrder = purchaseOrderDao.findById(1L);
        ProductSpec productSpec = new ProductSpec();
        productSpec.setProductSpecId(1L);
        PurchaseOrderItem purchaseOrderItem1 = new PurchaseOrderItem();
        purchaseOrderItem1.setProductSpec(productSpec);
        purchaseOrderItem1.setQuantity(100);

        purchaseOrder.addPurchaseOrderItem(purchaseOrderItem1);
//        purchaseOrder.removePurchaseOrderItemAt(0);
        purchaseOrderDao.update(purchaseOrder);
//        purchaseOrderDao.delete(1L);ß
        System.out.print("234");
    }
}
