package cn.edu.nju.raledon.model.payment;

/**
 * Created by rale on 7/10/17.
 * 收入和支出
 */
public enum  PaymentType {

    CASH("现金支付"),
    ALIPAY("支付宝"),
    WEPAY("微信支付");

    private String message;

    PaymentType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static PaymentType fromCode(int code){
        switch (code){
            case 0 : return PaymentType.CASH;
            case 1 : return PaymentType.ALIPAY;
            case 2 : return PaymentType.WEPAY;
            default: return null;
        }
    }
}
