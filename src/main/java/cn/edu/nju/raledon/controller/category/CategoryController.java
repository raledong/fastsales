package cn.edu.nju.raledon.controller.category;

import cn.edu.nju.raledon.model.product.Category;
import cn.edu.nju.raledon.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by rale on 7/12/17.
 *  商品类型管理Controller
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据关键词搜索产品的类别
     * @param keyword
     * @return
     */
    @RequestMapping(value = "search/{keyword}", method = RequestMethod.GET)
    public @ResponseBody
    List<Category> search(@PathVariable(value = "keyword") String keyword){
        return categoryService.search(keyword);
    }

    @RequestMapping(value="all", method = RequestMethod.GET)
    public @ResponseBody List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
