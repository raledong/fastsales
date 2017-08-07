package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.ProductDao;
import cn.edu.nju.raledon.model.product.Product;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by rale on 7/9/17.
 * ProductDao实现类
 */
@Repository
@Transactional
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }
    @Override
    public List<Product> search(String keyword) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", "%"+keyword+"%");
        return this.findByNamedQueryAndParams("Product.searchProductByKeyword", map);
    }

    @Override
    public Product findByShortId(String shortId) {
        return (Product) this.currentSession().bySimpleNaturalId(Product.class).load(shortId);
    }

    @Override
    public Product findBySerializedId(String serializedId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("serializedId", serializedId);
        List<Product> products = this.findByNamedQueryAndParams("Product.searchProductBySerializedId", map);
        return products==null || products.isEmpty() ? null : products.get(0);
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", categoryId);
        return this.findByNamedQueryAndParams("Product.searchProductByCategoryId", map);
    }

    @Override
    public List<Product> findByMultipleCondition(Map<String, Object> conditions) {
        Criteria criteria = this.currentSession().createCriteria(Product.class);
        Iterator iterator = conditions.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String)iterator.next();
            criteria.add(Restrictions.like(key, "%"+conditions.get(key)+"%"));
        }
        return criteria.list();
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        ProductDao productDao = context.getBean(ProductDao.class);
//        Product product = new Product();
//        Category c = new Category();
//        c.setCategoryId(1);
//        product.setCategory(c);
//        product.setSerializedId("12313123");
//        product.setShortId("12312312");
//        product.setName("裤子2");
//        product.setTags(Arrays.asList(new String[]{"123","456"}));
//        ProductSpec productSpec1 = new ProductSpec();
//        productSpec1.setDetail("hhhhh");
//        productSpec1.setQuantity(10);
//        product.addProductSpec(productSpec1);
//        productDao.add(product);
//        Product product = productDao.findById(7L);
//        System.out.print(product.getName());
        productDao.findByShortId("13");
        System.out.println(productDao.findByCategoryId(1).size());
    }



}
