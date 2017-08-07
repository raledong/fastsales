package cn.edu.nju.raledon.service.customer;

import cn.edu.nju.raledon.dao.CustomerDao;
import cn.edu.nju.raledon.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rale on 7/20/17.
 * 客户管理模块实现类
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> searchCustomerByKeyword(String keyword) {
        return customerDao.searchCompleteInfo(keyword);
    }
}
