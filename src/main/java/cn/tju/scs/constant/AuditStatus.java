package cn.tju.scs.constant;

/**
 * Created by lichen.ll on 2016/9/8.
 */
public interface AuditStatus {

    //审核中
    Integer PENDING = 1;

    //审核成功
    Integer SUCCESS = 2;

    //审核失败
    Integer FAIL = 3;
}
