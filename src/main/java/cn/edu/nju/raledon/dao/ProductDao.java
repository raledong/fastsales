package cn.edu.nju.raledon.dao;

import cn.edu.nju.raledon.model.product.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/9/17.
 * 商品DAO
 */
public interface ProductDao extends GenericDao<Product, Long> {
    /**
     * 根据关键词查找商品
     * @param keyword
     * @return
     */
    List<Product> search(String keyword);

    /**
     * 根据货号搜索产品
     * @param shortId
     * @return
     */
    Product findByShortId(String shortId);

    /**
     * 根据序列号搜索产品
     * @param serializedId
     * @return
     */
    Product findBySerializedId(String serializedId);

    /**
     * 根据商品种类查找商品
     * @param categoryId
     * @return
     */
    List<Product> findByCategoryId(int categoryId);

    /**
     * 根据不同的输入条件来获得结果集
     * @param conditions
     * @return
     */
    List<Product> findByMultipleCondition(Map<String, Object> conditions);
}
