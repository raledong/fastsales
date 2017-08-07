package cn.edu.nju.raledon.dao;

import cn.edu.nju.raledon.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/8/17.
 * 客户信息DAO
 */
public interface CustomerDao extends GenericDao<Customer, Long>{
    /**
     * 根据关键词查找顾客，关键词的信息包括顾客的姓名，顾客的ID等
     * @param keyword
     * @param count
     * @return 返回客户完整信息或是其名称加ID
     */
    List<Customer> searchCompleteInfo(String keyword, int startIndex, int count);
    List<Customer> searchCompleteInfo(String keyword);

}
