package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.CustomerDao;
import cn.edu.nju.raledon.model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/8/17.
 * CustomerDao实现类
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Long> implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }

    @Override
    public List<Customer> searchCompleteInfo(String keyword, int startIndex, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", "%"+keyword+"%");
        return this.findByNamedQueryAndParams("Customer.selectByKeyword", map, startIndex, count);
    }

    @Override
    public List<Customer> searchCompleteInfo(String keyword) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", "%"+keyword+"%");
        return this.findByNamedQueryAndParams("Customer.selectByKeyword", map);
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        CustomerDao customerDao = context.getBean(CustomerDao.class);
        System.out.println(customerDao.searchCompleteInfo("rale", 0,2));
    }

}
