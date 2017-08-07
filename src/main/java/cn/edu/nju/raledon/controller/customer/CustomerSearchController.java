package cn.edu.nju.raledon.controller.customer;

import cn.edu.nju.raledon.controller.customer.bean.CustomerBean;
import cn.edu.nju.raledon.model.Customer;
import cn.edu.nju.raledon.service.customer.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 7/20/17.
 * 客户信息管理类
 */
@RequestMapping(value = "customer")
@Controller
public class CustomerSearchController {

    @Autowired
    private CustomerService customerService;

    @JsonView(CustomerBean.BriefView.class)
    @RequestMapping(value = "brief/{keyword}", method = RequestMethod.GET)
    public @ResponseBody List<Customer> searchCustomerBriefly(@PathVariable("keyword")String keyword){
        if (keyword!=null && !keyword.isEmpty()){
            return customerService.searchCustomerByKeyword(keyword);
        }
        return new ArrayList<Customer>();
    }

    @JsonView(CustomerBean.BriefView.class)
    @RequestMapping(value = "brief", method = RequestMethod.POST)
    public @ResponseBody List<Customer> searchCustomerBrieflyByPost(String keyword){
        if (keyword!=null && !keyword.isEmpty()){
            return customerService.searchCustomerByKeyword(keyword);
        }
        return new ArrayList<Customer>();
    }

    @JsonView(CustomerBean.DetailView.class)
    @RequestMapping(value = "detail/{keyword}", method = RequestMethod.GET)
    public @ResponseBody List<Customer> searchCustomerDetail(@PathVariable("keyword")String keyword){
        if (keyword!=null && !keyword.isEmpty()){
            return customerService.searchCustomerByKeyword(keyword);
        }
        return new ArrayList<Customer>();
    }

    @JsonView(CustomerBean.DetailView.class)
    @RequestMapping(value = "detail/{keyword}/{startat}/{count}", method = RequestMethod.GET)
    public @ResponseBody List<Customer> searchCustomerComplete(@PathVariable("keyword")String keyword){
        if (keyword!=null && !keyword.isEmpty()){
            return customerService.searchCustomerByKeyword(keyword);
        }
        return new ArrayList<Customer>();
    }
}
