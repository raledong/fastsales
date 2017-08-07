package cn.edu.nju.raledon.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Created by rale on 5/28/17.
 * 标签 内容和id
 */

@NamedQueries({
        @NamedQuery(
                name = "Tag.selectTagByKeyword",
                query = "select t.info from Tag t where t.info like :keyword or t.comment like :keyword order by t.info"
        )
})
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long tagId;

    @NaturalId
    @Column(name = "tag_info", nullable = false, unique = true)
    private String info;

    @Column(name = "tag_comment")
    private String comment;

    public Long getTagId() {
        return tagId;
    }
    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
