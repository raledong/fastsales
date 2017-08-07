package cn.edu.nju.raledon.service.product;

import cn.edu.nju.raledon.controller.product.bean.ProductBean;
import cn.edu.nju.raledon.model.product.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/12/17.
 * 商品信息管理模块
 */
public interface ProductService {

    /**
     * 创建新的商品
     * @param product
     */
    void createNewProduct(Product product);

    /**
     * 更新商品信息
     * @param product
     */
    void updateProduct(Product product);

    /**
     * 删除商品 如果商品库存仍有剩余 则暂时不允许删除
     * @param productId
     */
    void deleteProduct(Long productId);

    /**
     * 获得所有商品
     * @return
     */
    List<ProductBean> getAllProducts();

    /**
     * 根据关键词搜索商品
     * @param keyword
     * @return
     */
    List<ProductBean> searchProductByKeyword(String keyword);

    /**
     * 根据商品种类获得商品
     * @param categoryId
     * @return
     */
    List<ProductBean> searchProductByCategory(int categoryId);

    /**
     * 根据商品货号查找商品
     * @param shortId
     * @return
     */
    ProductBean searchProductByShortId(String shortId);

    /**
     * 根据商品条形码查找商品
     * @param serializedId
     * @return
     */
    ProductBean searchProductBySerializedId(String serializedId);
    //后序补充分页查询

}
