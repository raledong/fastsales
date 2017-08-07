package cn.edu.nju.raledon.dao;

import cn.edu.nju.raledon.model.sales.SalesItem;

import java.util.List;

/**
 * Created by rale on 7/15/17.
 * 销售单品管理，销售单品接收的操作包括商品的上架下架
 */
public interface SalesItemDao extends GenericDao<SalesItem, Long> {

    /**
     * 根据条形码号查找相应的销售单品
     * @param serializedId
     * @return
     */
    SalesItem findBySerializedId(String serializedId);

    /**
     * 根据关键词查找相关的商品
     * @param keyword
     * @return
     */
    List<SalesItem> findByKeyword(String keyword);
}
