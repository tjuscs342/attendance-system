package cn.tju.scs.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public @Data class ApplyDO {
    //用户id
    private Long userId;
    //申请的id
    private Long applicationId;
    //申请日期
    private Date applyDate;
    //审核日期
    private Date auditDate;
    //请假开始日期
    private Date startDate;
    //请假结束日期
    private Date endDate;
    //申请理由
    private String reason;
    //申请类型
    private Integer applyType;
    //审批结果
    private Integer result;
    //审批人id
    private Long operatorId;
    //审批人姓名
    private String operatorName;
    //审批意见
    private String remark;
}
