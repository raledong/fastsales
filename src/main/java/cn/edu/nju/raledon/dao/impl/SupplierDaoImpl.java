package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.GenericDao;
import cn.edu.nju.raledon.dao.SupplierDao;
import cn.edu.nju.raledon.model.Supplier;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by rale on 7/8/17.
 * SupplierDao实现类
 */
public class SupplierDaoImpl extends GenericDaoImpl<Supplier, Long> implements SupplierDao {


    public SupplierDaoImpl() {
        super(Supplier.class);
    }


}
