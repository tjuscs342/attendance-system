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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liu on 16-10-26.
 */
@Service("marryApplyOperate")
public class MarryApplyOperate implements ApplyOperate,AuditOperate {
    @Resource
    ApplyManager applyManager;
    @Resource
    AuditManager auditManager;
    @Resource
    UserManager userManager;

    @Override
    public void doOperate(Long userId , Date startDate , Date endDate , String reason) throws BLLException{
        int days = DateUtils.getDuration(startDate,endDate);
        applyManager.clearUselessApply(userId, ApplyTypes.APPLY_MARRY   );
        UserDO userDO = userManager.getUserInfoById(userId);
        if (userDO == null)
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR);
        Integer sex = userDO.getSex();
        Integer age = userDO.getAge();
        Integer marryTimes = userDO.getMarryTimes();
        if (((sex == 0 && age < 25) || (sex == 1 && age < 23) || (marryTimes != 0)) && (days > MarryApplySalaryRule.DAYS_OF_REMARRY)){
            throw Exceptions.newBLLException( ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH_REMARRY );
        }
        if(((sex == 0 && age >= 25) || (sex == 1 && age >= 23)) && (marryTimes == 0) && (days >MarryApplySalaryRule.DAYS_OF_MARRY)){
            throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH_MARRY);
        }

        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.APPLY_MARRY, reason);
    }
    public void auditPass(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId, AuditStatus.SUCCESS,remark,operatorId,operatorName);

    }

    @Override
    public void auditFail(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId,AuditStatus.FAIL,remark,operatorId,operatorName);
    }
}
