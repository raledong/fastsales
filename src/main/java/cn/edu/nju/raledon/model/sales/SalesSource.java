package cn.edu.nju.raledon.model.sales;

/**
 * Created by rale on 7/15/17.
 * 销售渠道枚举类
 */
public enum SalesSource {
    SHOP("实体店"),
    WECHAT("微店"),
    TAOBAO("淘宝");

    private String message;

    SalesSource(String message) {
        this.message = message;
    }

    static SalesSource fromCode(int salesSourceId){
        switch (salesSourceId){
            case 0 : return SalesSource.SHOP;
            case 1 : return SalesSource.WECHAT;
            case 2 : return SalesSource.TAOBAO;
            default : return null;
        }
    }
}
