package cn.edu.nju.raledon.service.sales;

import cn.edu.nju.raledon.controller.sales.bean.BriefSalesItemBean;
import cn.edu.nju.raledon.controller.sales.bean.SalesItemBean;
import cn.edu.nju.raledon.model.sales.SalesItem;

import java.util.List;

/**
 * Created by rale on 7/18/17.
 * 销售单品管理
 */
public interface SalesItemService {

    /**
     * 创建销售单品
     * @param salesItemBean
     */
    void createSalesItem(SalesItemBean salesItemBean);

    /**
     * 根据ID删除销售单品
     * @param salesItemId
     */
    void deleteSalesItemById(Long salesItemId);

    /**
     * 根据条形码查找商品
     * @param serializedId
     * @return
     */
    SalesItem searchSalesItemBySerializedId(String serializedId);
    boolean serializedIdExists(String serializedId);


}
