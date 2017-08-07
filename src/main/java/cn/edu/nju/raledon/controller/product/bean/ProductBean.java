package cn.edu.nju.raledon.controller.product.bean;

import cn.edu.nju.raledon.model.product.Product;
import cn.edu.nju.raledon.model.product.ProductSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 7/12/17.
 * 商品VO
 */
public class ProductBean {


    private String token;

    private Long productId;

    private String name;

    private String shortId;

    private String serializedId;

    private int categoryId;

    private String comment;

    private List<String> tags;


    private List<ProductSpecBean> productSpecBeans;

    public ProductBean() {
        productSpecBeans = new ArrayList<ProductSpecBean>();
    }

    public ProductBean(String token, String name, String shortId, String serializedId, int categoryId, String comment, List<String> tags, List<ProductSpecBean> productSpecBeans) {
        this.token = token;
        this.name = name;
        this.shortId = shortId;
        this.serializedId = serializedId;
        this.categoryId = categoryId;
        this.comment = comment;
        this.tags = tags;
    }

    public ProductBean(Product product){
        this.productId = product.getProductId();
        this.name = product.getName();
        this.shortId = product.getShortId();
        this.serializedId = product.getSerializedId();
        this.categoryId = product.getCategory().getCategoryId();
        this.comment = this.getComment();
        this.tags = this.getTags();
        this.setProductSpec(product.getProductSpecList());

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public String getSerializedId() {
        return serializedId;
    }

    public void setSerializedId(String serializedId) {
        this.serializedId = serializedId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public List<ProductSpecBean> getProductSpecBeans() {
        return productSpecBeans = (productSpecBeans==null)? new ArrayList<ProductSpecBean>() : productSpecBeans;
    }

    public void setProductSpecBeans(List<ProductSpecBean> productSpecBeans) {
        this.productSpecBeans = productSpecBeans;
    }
    public void setProductSpec(List<ProductSpec> productSpecs){
        if (this.getProductSpecBeans()==null) this.productSpecBeans = new ArrayList<ProductSpecBean>();
        if (productSpecs != null){
            for (ProductSpec tempProductSpec : productSpecs){
                this.addProductSpec(tempProductSpec);
            }
        }
    }

    public void addProductSpec(ProductSpec productSpec){
        this.getProductSpecBeans().add(new ProductSpecBean(productSpec));
    }

}
