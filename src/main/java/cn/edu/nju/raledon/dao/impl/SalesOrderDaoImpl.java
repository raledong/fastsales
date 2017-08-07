package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.SalesOrderDao;
import cn.edu.nju.raledon.model.Customer;
import cn.edu.nju.raledon.model.product.ProductSpec;
import cn.edu.nju.raledon.model.sales.SalesItem;
import cn.edu.nju.raledon.model.sales.SalesOrder;
import cn.edu.nju.raledon.model.sales.SalesOrderItem;
import cn.edu.nju.raledon.model.sales.SalesSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * Created by rale on 7/15/17.
 * SalesOrderDao实现类
 */
@Repository
public class SalesOrderDaoImpl extends GenericDaoImpl<SalesOrder, Long> implements SalesOrderDao{
    public SalesOrderDaoImpl() {
        super(SalesOrder.class);
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        SalesOrderDao salesOrderDao = context.getBean(SalesOrderDao.class);
//        SalesOrder salesOrder = new SalesOrder();
//        salesOrder.setSalesSource(SalesSource.TAOBAO);
//        salesOrder.setUserId(1L);
//        salesOrder.setCustomer(new Customer(1L));
//
//        SalesItem salesItem = new SalesItem();
//        salesItem.setSalesItemId(1L);
//        SalesOrderItem salesOrderItem = new SalesOrderItem();
//        salesOrderItem.setQuantity(100);
//        salesOrderItem.setSalesItem(salesItem);
//        salesOrder.addSalesOrderItem(salesOrderItem);
//        salesOrder.setSalesOrderId(2L);
//        salesOrderDao.add(salesOrder);

        SalesOrder salesOrder = salesOrderDao.findById(2L);
        System.out.println(salesOrder.getTotalPrice());
    }
}
