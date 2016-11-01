package cn.tju.scs.service.impl;


import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.manager.impl.ApplyManagerImpl;
import cn.tju.scs.manager.impl.AuditManagerImpl;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.AuditOperate;
import cn.tju.scs.util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Liu on 2016/10/23.
 */
public class SickApplyOperate implements ApplyOperate,AuditOperate {
    @Resource
    ApplyManagerImpl applyManager;

    @Resource
    AuditManagerImpl auditManager;

    @Override
    public void doOperate(Long userId , Date startDate , Date endDate ,String reason) throws BLLException{
        int days = DateUtils.getDuration(startDate,endDate);
        applyManager.clearUselessApply(userId, ApplyTypes.APPLY_SICK);
        List<ApplyDO> list = applyManager.selectApplysByType(userId, ApplyTypes.APPLY_SICK);
        if(list == null)
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);

        int used = 0;
        for(ApplyDO item : list){
            if (!DateUtils.checkUseless(item.getApplyDate()) && item.getResult().equals(AuditStatus.SUCCESS))
                used += DateUtils.getDuration(item.getStartDate(), item.getEndDate());
        }
        SickApplySalaryRule sickApplySalaryRule = new SickApplySalaryRule();
//        int salaryDays = 0;
//        if(days + used > SickApplySalaryRule.DAYS_OF_SICK) salaryDays = sickApplySalaryRule.getSalaryInc(days + used);

        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.APPLY_SICK, reason);
    }

    public void auditPass(String remark, Long operatorId, String operatorName, Long applicationId)throws BLLException{
        auditManager.auditApply(applicationId, AuditStatus.SUCCESS,remark,operatorId,operatorName);
    }

    public void auditFail(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId,AuditStatus.FAIL,remark,operatorId,operatorName);
    }
}