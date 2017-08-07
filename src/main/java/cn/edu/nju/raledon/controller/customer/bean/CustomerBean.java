package cn.edu.nju.raledon.controller.customer.bean;

/**
 * Created by rale on 7/20/17.
 * 返回客户信息的VO
 */
public class CustomerBean {
    public interface BriefView{};
    public interface DetailView extends BriefView{};
    public interface CompleteView extends DetailView{};
}
