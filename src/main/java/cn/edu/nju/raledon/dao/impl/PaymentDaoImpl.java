package cn.edu.nju.raledon.dao.impl;

import cn.edu.nju.raledon.dao.PaymentDao;
import cn.edu.nju.raledon.model.payment.Payment;
import cn.edu.nju.raledon.model.payment.PaymentType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

/**
 * Created by rale on 7/10/17.
 * PaymentDao实现类
 */
@Repository
public class PaymentDaoImpl extends GenericDaoImpl<Payment, Long> implements PaymentDao
{
    public PaymentDaoImpl() {
        super(Payment.class);
    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/applicationContext.xml", "META-INF/infrastructure.xml"});
        PaymentDao paymentDao = context.getBean(PaymentDao.class);
//        Payment payment = new Payment();
//        payment.setPaymentId(200L);
//        payment.setPaymentType(PaymentType.SALARY);
//        payment.setSum(200);
//        paymentDao.add(payment);
        Payment payment = paymentDao.findById(10L);
        System.out.print(payment.getPaymentType());
    }
}
