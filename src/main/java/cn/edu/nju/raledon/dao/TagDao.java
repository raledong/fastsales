package cn.edu.nju.raledon.dao;

import cn.edu.nju.raledon.model.Tag;

import java.util.List;

/**
 * Created by rale on 7/8/17.
 * 商品的标签，也可以是其它的标签
 */
public interface TagDao extends GenericDao<Tag, Long> {
    /**
     * 根据关键词搜索不超过分页数量的标签
     * @param keyword
     * @param count
     * @return
     */
    List<String> search(String keyword, int count);
    List<String> search(String keyword);
}
