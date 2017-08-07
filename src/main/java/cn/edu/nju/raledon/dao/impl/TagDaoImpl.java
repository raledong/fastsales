package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.TagDao;
import cn.edu.nju.raledon.model.Tag;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/8/17.
 * TagDao实现类
 */
@Repository
public class TagDaoImpl extends GenericDaoImpl<Tag, Long> implements TagDao {
    public TagDaoImpl() {
        super(Tag.class);
    }

    /**
     * 根据关键词和数量检索相关的标签
     * @param keyword
     * @param count
     * @return 检索标签列表
     */
    @Override
    public List<String> search(String keyword, int count) {
        String hql = "select t.info from Tag t where info like :keyword or t.comment like :keyword order by t.info";
        Query q = this.currentSession().createQuery(hql);
        q.setParameter("keyword", "%"+keyword+"%");
        q.setMaxResults(count);
        return q.list();
    }

    @Override
    public List<String> search(String keyword) {
        Map<String, Object> map = new HashMap<String, Object>();
        Query namedQuery = this.currentSession().getNamedQuery("Tag.selectTagByKeyword");
        namedQuery.setParameter("keyword", "%"+keyword+"%");
        return namedQuery.list();
    }



    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        TagDao tagDao = context.getBean(TagDao.class);
        System.out.print(tagDao.search("test"));
    }
}
