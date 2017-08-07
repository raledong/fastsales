package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.SalesItemDao;
import cn.edu.nju.raledon.model.sales.SalesItem;
import cn.edu.nju.raledon.model.sales.SalesItemSpec;
import cn.edu.nju.raledon.util.UUIDGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rale on 7/15/17.
 * SalesItemDao实现类
 */
@Repository
public class SalesItemDaoImpl extends GenericDaoImpl<SalesItem, Long> implements SalesItemDao {

    public SalesItemDaoImpl() {
        super(SalesItem.class);
    }

    @Override
    public SalesItem findBySerializedId(String serializedId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("serializedId", serializedId);
        List<SalesItem> salesItemList = this.findByNamedQueryAndParams("SalesItem.searchSalesItemBySerializedId", map);
        return salesItemList==null || salesItemList.isEmpty() ? null : salesItemList.get(0);
    }

    @Override
    public List<SalesItem> findByKeyword(String keyword) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", "%"+keyword+"%");
        return this.findByNamedQueryAndParams("SalesItem.searchSalesItemByKeyword", map);
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        SalesItemDao salesItemDao = context.getBean(SalesItemDao.class);
//        SalesItem salesItem = new SalesItem();
//        salesItem.setName("第一个上架单品");
//        salesItem.setSerializedId(UUIDGenerator.generateUUID());
//        SalesItemSpec salesItemSpec = new SalesItemSpec(1L, 10);
//        salesItem.addSalesItemSpec(salesItemSpec);
//        salesItemDao.add(salesItem);
        SalesItem salesItem = salesItemDao.findBySerializedId("1123123");
        System.out.println("hello");
    }


}
