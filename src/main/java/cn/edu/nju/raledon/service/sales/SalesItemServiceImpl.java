package cn.edu.nju.raledon.service.sales;

import cn.edu.nju.raledon.controller.sales.bean.SalesItemBean;
import cn.edu.nju.raledon.dao.SalesItemDao;
import cn.edu.nju.raledon.model.sales.SalesItem;
import cn.edu.nju.raledon.properties.Properties;
import cn.edu.nju.raledon.util.SnowflakeIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rale on 7/18/17.
 * SalesItemService实现类
 */
@Transactional
@Service
public class SalesItemServiceImpl implements SalesItemService {
    @Autowired
    private SalesItemDao salesItemDao;

    public SnowflakeIDGenerator snowflakeIDGenerator = new SnowflakeIDGenerator(Properties.SALES_ITEM_UUID_GENERATOR_WORKID, Properties.SALES_ITEM_UUID_GENERATOR_DATACENTERID);

    @Override
    public void createSalesItem(SalesItemBean salesItemBean) {
        SalesItem salesItem = new SalesItem(salesItemBean);
        if (salesItem.getSerializedId()==null || salesItem.getSerializedId().isEmpty()) salesItem.setSerializedId(snowflakeIDGenerator.nextId()+"");
        salesItemDao.add(salesItem);
    }

    @Override
    public void deleteSalesItemById(Long salesItemId) {
        salesItemDao.delete(salesItemId);
    }

    @Override
    public SalesItem searchSalesItemBySerializedId(String serializedId) {
        if(serializedId==null || serializedId.isEmpty()) return null;
        return salesItemDao.findBySerializedId(serializedId);
    }

    @Override
    public boolean serializedIdExists(String serializedId) {
        return searchSalesItemBySerializedId(serializedId)==null;
    }
}
