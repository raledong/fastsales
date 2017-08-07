package cn.edu.nju.raledon.controller.sales;

import cn.edu.nju.raledon.controller.product.bean.ProductBean;
import cn.edu.nju.raledon.controller.sales.form.SalesOrderForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by rale on 8/1/17.
 * 销售单管理接口
 */
@RequestMapping("salesorder")
@Controller
public class SalesOrderController {

    /**
     * 返回添加页面
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(){
        return "sales_order_create";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@RequestBody SalesOrderForm salesOrderForm, @RequestParam double hasPaidSum, @RequestParam HttpSession httpSession){
        return "sales_order_create";
    }
}
