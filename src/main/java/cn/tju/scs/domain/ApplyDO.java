package cn.tju.scs.domain;

import cn.tju.scs.util.JSONBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public class ApplyDO {
    //用户id
    private Long userId;
    //用户名
    private String userName;
    //申请的id
    private Long applicationId;
    //申请日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDate;
    //审核日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditDate;
    //请假开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    //请假结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
    @JsonSerialize(using = JSONBuilder.JsonDateSerializer.class)
    public Date getApplyDate() {
        return applyDate;
    }

    @JsonDeserialize(using = JSONBuilder.JsonDateDeserializer.class)
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }


    @JsonSerialize(using = JSONBuilder.JsonDateSerializer.class)
    public Date getAuditDate() {
        return auditDate;
    }

    @JsonDeserialize(using = JSONBuilder.JsonDateDeserializer.class)
    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    @JsonSerialize(using = JSONBuilder.JsonDateSerializer.class)
    public Date getStartDate() {
        return startDate;
    }

    @JsonDeserialize(using = JSONBuilder.JsonDateDeserializer.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonSerialize(using = JSONBuilder.JsonDateSerializer.class)
    public Date getEndDate() {
        return endDate;
    }

    @JsonDeserialize(using = JSONBuilder.JsonDateDeserializer.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName(){return userName;}

    public void setUserName(String userName){   this.userName = userName;}
}
