package cn.edu.nju.raledon.model.product;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rale on 7/9/17.
 * 商品的分类
 */
@NamedQueries({
    @NamedQuery(
            name = "Category.selectByKeyword",
            query = "select c from Category c where c.name like :keyword or c.comment like :keyword order by c.name"
    )
}
)
@Entity
@Table(name = "category")
public class Category {
    /**种类id**/
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private int categoryId;

    /**类别内容**/
    @NaturalId
    @Column(name = "category_name", nullable = false, unique = true)
    private String name;

    /**类别备注**/
    @Column(name = "category_comment")
    private String comment;

    /**类别创建时间**/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "category_created_at")
    private Date createdAt;

//    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SELECT)
//    private List<Product> productList;


    public Category() {
    }

    public Category(int categoryId){
        this.setCategoryId(categoryId);
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

//    public List<Product> getProductList() {
//        return productList;
//    }
//
//    public void setProductList(List<Product> productList) {
//        this.productList = productList;
//    }

}
