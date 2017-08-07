package cn.edu.nju.raledon.service.payment;

/**
 * Created by rale on 8/7/17.
 * 支付端口，支付端口允许的支付方式包括现金支付，支付宝支付，微信支付
 */
public interface PaymentService {

    boolean payByCash(double sum, Long salesOrderId, String comment);

}
