package cn.edu.nju.raledon.dao;

import cn.edu.nju.raledon.model.product.Category;

import java.util.List;

/**
 * Created by rale on 7/9/17.
 * 商品分类DAO
 */
public interface CategoryDao extends GenericDao<Category, Integer>{
    /**
     * 根据关键词搜索相关的分类
     * @param keyword
     * @return List<Category>
     */
    List<Category> search(String keyword);
    List<Category> search(String keyword, int count);
}
