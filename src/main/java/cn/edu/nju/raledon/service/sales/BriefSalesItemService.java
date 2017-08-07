package cn.edu.nju.raledon.service.sales;

import cn.edu.nju.raledon.controller.sales.bean.BriefSalesItemBean;

import java.util.List;

/**
 * Created by rale on 8/1/17.
 * 销售品缩减版数据
 */
public interface BriefSalesItemService {

    /**
     * 根据关键词检索 并返回简约形式
     * @param keyword
     * @return
     */
    List<BriefSalesItemBean> searchSalesItemByKeyword(String keyword);

    /**
     * 根据关键词检索，返回所有相关销售品的简约形式
     * @return
     */
    List<BriefSalesItemBean> getAll();
}
