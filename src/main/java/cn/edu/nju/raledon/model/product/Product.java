package cn.edu.nju.raledon.model.product;

import cn.edu.nju.raledon.controller.product.bean.ProductBean;
import cn.edu.nju.raledon.controller.product.bean.ProductSpecBean;
import cn.edu.nju.raledon.converter.ListToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rale on 7/9/17.
 * 商品在库存中的形式
 */
@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "Product.searchProductByKeyword",
                    query = "select p from Product p where p.shortId like :keyword or p.serializedId like :keyword or p.name like :keyword or p.tags like :keyword or p.comment like :keyword order by p.shortId"
        ),
        @NamedQuery(name = "Product.searchProductBySerializedId",
                    query = "select p from Product p where p.serializedId = :serializedId"
        ),
        @NamedQuery(name = "Product.searchProductByCategoryId",
                    query = "select p from Product p where p.category.categoryId = :categoryId"
        )
}
)
@FilterDefs({
        @FilterDef(name = "User.serializedId")
})
public class Product {

    public Product() {
    }

    public Product(ProductBean productBean){
        this(productBean.getShortId(), productBean.getSerializedId(), productBean.getCategoryId(), productBean.getName(), null, productBean.getTags(), productBean.getComment());
        if(productBean.getProductSpecBeans() !=null && productBean.getProductSpecBeans().size()>0){
            for (ProductSpecBean productSpecBean: productBean.getProductSpecBeans()){
                this.addProductSpec(new ProductSpec(productSpecBean));
            }
        }
    }

    public Product(String shortId, String serializedId, int categoryId, String name, String coverImageUrl, List<String> tags, String comment) {
        this.productId = productId;
        this.shortId = shortId;
        this.serializedId = serializedId;
        this.category = new Category(categoryId);
        this.name = name;
        this.coverImageUrl = coverImageUrl;
        this.tags = tags;
        this.comment = comment;
    }

    @Id
    @Column(name="product_id")
    private Long productId;

    /**货号**/
    @NaturalId
    @Column(name = "product_short_id", nullable = false, unique = true)
    private String shortId;

    /**条形码**/
    @Column(name = "product_serialized_id", nullable = true)
    private String serializedId;

    /**商品类型**/
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;

    @Column(name = "product_name", nullable = false, unique = true)
    private String name;

    @Column(name = "product_cover_image_url")
    private String coverImageUrl;

    @Convert(converter = ListToStringConverter.class)
    @Column(name = "product_tags")
    private List<String> tags;

    @Column(name = "product_comment")
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "product_created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "product_spec_id")
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties("product")
    private List<ProductSpec> productSpecList;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Category getCategory() {
        return category;
    }

    public String getCategoryName(){
        return getCategory().getName();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void addProductSpec(ProductSpec productSpec){
        if(productSpecList==null){
            productSpecList = new ArrayList<ProductSpec>();
        }
        productSpec.setProduct(this);
        productSpecList.add(productSpec);
    }

    public List<ProductSpec> getProductSpecList() {
        return productSpecList==null ? new ArrayList<ProductSpec>() : productSpecList;
    }

    public void setProductSpecList(List<ProductSpec> productSpecList) {
        this.productSpecList = productSpecList;
    }

    public int totalQuantity(){
        int sum = 0;
        for (ProductSpec tempProductSpec : getProductSpecList()){
            sum += tempProductSpec.getQuantity();
        }
        return sum;
    }
}
