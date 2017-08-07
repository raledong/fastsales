package cn.edu.nju.raledon.service.customer;

import cn.edu.nju.raledon.model.Customer;

import java.util.List;

/**
 * Created by rale on 7/20/17.
 * 客户管理接口
 */
public interface CustomerService {

    /**
     * 根据关键词来搜索客户
     * @param keyword
     * @return
     */
    List<Customer> searchCustomerByKeyword(String keyword);
}
