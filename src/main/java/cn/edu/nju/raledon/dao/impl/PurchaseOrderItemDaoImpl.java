package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.PurchaseOrderItemDao;
import cn.edu.nju.raledon.model.purchase.PurchaseOrderItem;

/**
 * Created by rale on 7/10/17.
 */
public class PurchaseOrderItemDaoImpl extends GenericDaoImpl<PurchaseOrderItem, Long> implements PurchaseOrderItemDao {
    public PurchaseOrderItemDaoImpl() {
        super(PurchaseOrderItem.class);
    }
}
