package cn.edu.nju.raledon.controller.sales;

import cn.edu.nju.raledon.controller.sales.bean.BriefSalesItemBean;
import cn.edu.nju.raledon.controller.sales.bean.SalesItemBean;
import cn.edu.nju.raledon.interceptors.token.Token;
import cn.edu.nju.raledon.model.sales.SalesItem;
import cn.edu.nju.raledon.properties.SessionProperties;
import cn.edu.nju.raledon.service.sales.BriefSalesItemService;
import cn.edu.nju.raledon.service.sales.SalesItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/16/17.
 * 上架商品管理
 */
@Controller
@RequestMapping("salesitem")
public class SalesItemController {

    @Autowired
    private SalesItemService salesItemService;

    @Autowired
    private BriefSalesItemService briefSalesItemService;

    /**
     * 返回商品上架页面
     * @param session
     * @return
     */
    @Token(save = true)
    @RequestMapping(value = "puton", method = RequestMethod.GET)
    public ModelAndView add(HttpSession session){
        ModelMap modelMap = new ModelMap();
        SalesItemBean salesItemBean = new SalesItemBean();
        modelMap.put(SessionProperties.SALES_ITEM_ADD_BEAN, salesItemBean);
        return new ModelAndView("sales_puton_product", modelMap);
    }

    //@Todo 需要填写上传的用户的信息
    @Token(remove = true)
    @RequestMapping(value = "puton", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> add(@RequestBody SalesItemBean salesItemBean){
        Map<String,Object> map = new HashMap<String,Object>();
        salesItemService.createSalesItem(salesItemBean);
        map.put(SessionProperties.MESSAGE, SessionProperties.SUBMIT_SUCCESS);
        return map;
    }

    @RequestMapping(value = "seid/{serializedId}", method = RequestMethod.GET)
    public @ResponseBody
    SalesItem findBySerializedId(@PathVariable("serializedId")String serializedId){
        return salesItemService.searchSalesItemBySerializedId(serializedId);
    }



    /**
     * 根据关键词，返回先关销售商品的简约形式
     * @param keyword
     * @return
     */
    @RequestMapping(value = "b/keyword/{keyword}", method = RequestMethod.GET)
    public @ResponseBody
    List<BriefSalesItemBean> findByKeyword(@PathVariable("keyword")String keyword){
        if (keyword!=null && !keyword.isEmpty()){
            return briefSalesItemService.searchSalesItemByKeyword(keyword);
        }
        return new ArrayList<BriefSalesItemBean>();
    }

    /**
     * 获得所有销售商品，返回销售商品的简约形式
     * @return
     */
    @RequestMapping(value = "b/all", method = RequestMethod.GET)
    public @ResponseBody List<BriefSalesItemBean> findAll(){
        return briefSalesItemService.getAll();
    }

}
