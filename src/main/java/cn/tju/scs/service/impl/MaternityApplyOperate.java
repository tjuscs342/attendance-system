package cn.tju.scs.service.impl;

import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.manager.UserManager;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.AuditOperate;
import cn.tju.scs.util.DateUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liu on 16-10-30.
 */
public class MaternityApplyOperate implements ApplyOperate,AuditOperate{
    @Resource
    ApplyManager applyManager;
    @Resource
    AuditManager auditManager;
    @Resource
    UserManager userManager;

    @Override
    public void doOperate(Long userId, Date startDate, Date endDate, String reason) throws BLLException{
        UserDO userDO = new UserDO();
        int days = DateUtils.getDuration(startDate,endDate);
        userDO = userManager.getUserInfoById(userId);
        int age = userDO.getAge();
        if(userDO.getSex() == 1 && age >= 24){
            if(days > MaternityApplySalaryRule.DAYS_OF_MATERNITY) {
                throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH_MATERNITY);
            }
        }
        else if(userDO.getSex() == 1){
            if (days > MaternityApplySalaryRule.DAYS_OF_EARLY_MATERNITY){
                throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH_MATERNITY);
            }
        }
        else if(userDO.getSex() == 0){
            throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_NOT_SUIT_MATERNITY);
        }
        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.APPLY_MATERNITY, reason);

    }
    @Override
    public void auditPass(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId, AuditStatus.SUCCESS,remark,operatorId,operatorName);
    }

    @Override
    public void auditFail(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId,AuditStatus.FAIL,remark,operatorId,operatorName);
    }
}
