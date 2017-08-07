package cn.edu.nju.raledon.service.category;

import cn.edu.nju.raledon.model.product.Category;

import java.util.List;

/**
 * Created by rale on 7/12/17.
 * 商品分类管理模块
 */
public interface CategoryService {

    /**
     * 获得商品所有的类别
     * @return List<Category>
     */
    List<Category> getAllCategories();

    /**
     * 根据关键词搜索并且返回所有相关的类别
     * @param keyword
     * @return List<Category>
     */
    List<Category> search(String keyword);
}
