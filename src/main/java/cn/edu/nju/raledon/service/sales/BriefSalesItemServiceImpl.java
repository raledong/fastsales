package cn.edu.nju.raledon.service.sales;

import cn.edu.nju.raledon.controller.sales.bean.BriefSalesItemBean;
import cn.edu.nju.raledon.dao.SalesItemDao;
import cn.edu.nju.raledon.model.sales.SalesItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 8/1/17.
 * BriefSalesItemService实现类
 */
@Service
@Transactional
public class BriefSalesItemServiceImpl implements BriefSalesItemService {

    @Autowired
    private SalesItemDao salesItemDao;

    @Override
    public List<BriefSalesItemBean> searchSalesItemByKeyword(String keyword) {
        return this.toBriefSalesItemBean(salesItemDao.findByKeyword(keyword));
    }

    @Override
    public List<BriefSalesItemBean> getAll() {
        return toBriefSalesItemBean(salesItemDao.findAll());
    }

    private List<BriefSalesItemBean> toBriefSalesItemBean(List<SalesItem> salesItems){
        List<BriefSalesItemBean> salesItemBeans = new ArrayList<BriefSalesItemBean>();
        if(salesItems!=null && !salesItems.isEmpty()){
            for (SalesItem salesItem : salesItems){
                salesItemBeans.add(new BriefSalesItemBean(salesItem));
            }
        }
        return salesItemBeans;
    }


}
