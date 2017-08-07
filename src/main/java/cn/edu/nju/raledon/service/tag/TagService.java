package cn.edu.nju.raledon.service.tag;

import java.util.List;

/**
 * Created by rale on 7/12/17.
 * 标签管理类，目前使用的场景包括商品标签
 */
public interface TagService {
    /**
     * 根据关键词搜索标签
     * @param keyword
     * @param count 默认为10条
     * @return
     */
    List<String> search(String keyword, int count);
    List<String> search(String keyword);
}
