package cn.edu.nju.raledon.service.product;

import cn.edu.nju.raledon.controller.product.bean.ProductBean;
import cn.edu.nju.raledon.dao.ProductDao;
import cn.edu.nju.raledon.model.product.Product;
import cn.edu.nju.raledon.properties.Properties;
import cn.edu.nju.raledon.util.SnowflakeIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 7/12/17.
 * 商品信息管理模块实现类
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    private SnowflakeIDGenerator idGenerator = new SnowflakeIDGenerator(Properties.PRODUCT_UUID_GENERATOR_WORKID, Properties.PRODUCT_UUID_GENERATOR_DATACENTERID);
    @Override
    public void createNewProduct(Product product) {
        product.setProductId(idGenerator.nextId());
        productDao.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productDao.findById(productId);
        if (product.totalQuantity() == 0){
            productDao.delete(productId);
        }
    }

    @Override
    public List<ProductBean> getAllProducts() {
        List<Product> products = productDao.findAll();
        List<ProductBean> productBeans = new ArrayList<ProductBean>();
        if (products!=null && !products.isEmpty()){
            for (Product tempProduct : products){
                productBeans.add(new ProductBean(tempProduct));
            }
        }
        return productBeans;
    }

    @Override
    public List<ProductBean> searchProductByKeyword(String keyword) {
        List<Product> products = productDao.search(keyword);
        List<ProductBean> productBeans = new ArrayList<ProductBean>();
        if (products!=null && !products.isEmpty()){
            for (Product tempProduct : products){
                productBeans.add(new ProductBean(tempProduct));
            }
        }
        return productBeans;
    }

    @Override
    public List<ProductBean> searchProductByCategory(int categoryId) {
        List<Product> products = productDao.findByCategoryId(categoryId);
        List<ProductBean> productBeans = new ArrayList<ProductBean>();
        if (products!=null && !products.isEmpty()){
            for (Product tempProduct : products){
                productBeans.add(new ProductBean(tempProduct));
            }
        }
        return productBeans;
    }

    @Override
    public ProductBean searchProductByShortId(String shortId) {
        Product product = productDao.findByShortId(shortId);
        return product==null ? null : new ProductBean(product);
    }

    @Override
    public ProductBean searchProductBySerializedId(String serializedId) {
        Product product = productDao.findBySerializedId(serializedId);
        return product==null ? null : new ProductBean(product);
    }
}
