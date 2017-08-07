package cn.edu.nju.raledon.controller.product;

import cn.edu.nju.raledon.controller.GenericController;
import cn.edu.nju.raledon.controller.product.bean.ProductBean;
import cn.edu.nju.raledon.interceptors.token.Token;
import cn.edu.nju.raledon.model.product.Product;
import cn.edu.nju.raledon.properties.SessionProperties;
import cn.edu.nju.raledon.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/12/17.
 * 商品信息管理Controller
 */
@Controller
@RequestMapping(value = "product")
public class ProductManagementController extends GenericController{
    @Autowired
    private ProductService productService;

    @Token(save = true)
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView add( HttpServletRequest servletRequest){
        ModelMap modelMap = new ModelMap();
        ProductBean productBean = new ProductBean();
        modelMap.addAttribute(SessionProperties.PRODUCT_ADD_BEAN, productBean);
        return new ModelAndView("product_add", modelMap);
    }

    @Token(remove = true)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> add(@RequestBody ProductBean productBean, @RequestParam String token, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println(token);
        productService.createNewProduct(new Product(productBean));
        map.put(SessionProperties.MESSAGE, SessionProperties.SUBMIT_SUCCESS);
        return map;
    }

    /**
     * 根据关键词搜索商品啦
     * @param keyword
     * @return
     */
    @RequestMapping(value = "search/{keyword}", method = RequestMethod.GET)
    public @ResponseBody
    List<ProductBean> searchByKeyword(@PathVariable("keyword") String keyword){
        if (keyword!=null && !keyword.isEmpty()){
            return productService.searchProductByKeyword(keyword);
        }
        return new ArrayList<ProductBean>();
    }

    /**
     * 根据商品的类别
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "search/category/{categoryId}", method = RequestMethod.GET)
    public @ResponseBody List<ProductBean> searchByCategory(@PathVariable("categoryId") int categoryId){
        List<ProductBean> products = productService.searchProductByCategory(categoryId);
        return products;
    }
     /**
     * 根据货号查找商品
     * @param shortId
     * @return Map<String, Object>
     * 如果成功找到，则返回对象，否则返回报错信息
     */
    @RequestMapping(value = "shid/{sdvalue}")
    public @ResponseBody Map<String, Object > searchProductByShortId(@PathVariable("sdvalue") String shortId){
        HashMap<String, Object> map = new HashMap<String, Object>();

        if(shortId==null||shortId.isEmpty()){
            map.put(SessionProperties.MESSAGE, SessionProperties.CANT_BE_EMPTY);
        }else{
            ProductBean product = productService.searchProductByShortId(shortId);
            if(product!=null){
                map.put(SessionProperties.MESSAGE, SessionProperties.SUCCESS);
                map.put("product", product);
            }else{
                map.put(SessionProperties.MESSAGE, SessionProperties.IS_EMPTY);
            }
        }
        return map;
    }

    /**
     * 根据序列号查找商品
     * @param serializedId
     * @return
     */
    @RequestMapping(value = "seid/{seidvalue}")
    public @ResponseBody Map<String, Object> searchProductBySerializedId(@PathVariable("seidvalue")String serializedId){
        HashMap<String, Object> map = new HashMap<String, Object>();

        if(serializedId==null||serializedId.isEmpty()){
            map.put(SessionProperties.MESSAGE, SessionProperties.CANT_BE_EMPTY);
        }else{
            ProductBean product = productService.searchProductBySerializedId(serializedId);
            if(product!=null){
                map.put(SessionProperties.MESSAGE, SessionProperties.SUCCESS);
                map.put("product", product);
            }else{
                map.put(SessionProperties.MESSAGE, SessionProperties.IS_EMPTY);
            }
        }
        return map;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public @ResponseBody
    List<ProductBean> getAllProducts(){
        return productService.getAllProducts();
    }

}
