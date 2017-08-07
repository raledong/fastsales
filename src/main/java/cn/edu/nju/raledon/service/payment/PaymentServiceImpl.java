package cn.edu.nju.raledon.service.payment;

import cn.edu.nju.raledon.dao.PaymentDao;
import cn.edu.nju.raledon.dao.SalesOrderDao;
import cn.edu.nju.raledon.model.payment.Payment;
import cn.edu.nju.raledon.model.payment.PaymentType;
import cn.edu.nju.raledon.model.sales.SalesOrder;
import cn.edu.nju.raledon.properties.Properties;
import cn.edu.nju.raledon.util.SnowflakeIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rale on 8/7/17.
 * 支付端口的具体实现
 */
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Autowired
    private SalesOrderDao salesOrderDao;

    SnowflakeIDGenerator snowflakeIDGenerator = new SnowflakeIDGenerator(Properties.SALES_ORDER_UUID_GENERATOR_WORKID, Properties.SALES_ORDER_UUID_GENERATOR_DATACNTERID);
    @Override
    public boolean payByCash(double sum, Long salesOrderId, String comment) {
        SalesOrder salesOrder = salesOrderDao.findById(salesOrderId);
        if(salesOrder==null || salesOrder.getNotPaid()<sum) return false;
        Payment payment = new Payment(snowflakeIDGenerator.nextId(), sum, salesOrderId, PaymentType.CASH, comment);
        paymentDao.add(payment);
        return true;
    }
}
