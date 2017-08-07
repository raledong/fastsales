package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.CategoryDao;
import cn.edu.nju.raledon.model.product.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/9/17.
 * CategoryDao实现类
 */
@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category, Integer> implements CategoryDao{
    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> search(String keyword) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", "%"+keyword+"%");
        return this.findByNamedQueryAndParams("Category.selectByKeyword", map);
    }

    @Override
    public List<Category> search(String keyword, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", "%"+keyword+"%");
        return this.findByNamedQueryAndParams("Category.selectByKeyword", map,0, count);
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        CategoryDao categoryDao = context.getBean(CategoryDao.class);
        System.out.print(categoryDao.search("sock").size());
    }
}
