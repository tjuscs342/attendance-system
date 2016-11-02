package cn.tju.scs.service.impl;

import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.AuditOperate;
import cn.tju.scs.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liu on 16-10-30.
 */
@Service("eventApplyOperate")
public class EventApplyOperate implements ApplyOperate,AuditOperate{
    @Resource
    AuditManager auditManager;
    @Resource
    ApplyManager applyManager;

    @Override
    public void doOperate(Long userId , Date startDate , Date endDate , String reason) throws BLLException{
        int days = DateUtils.getDuration(startDate,endDate);
//        applyManager.clearUselessApply(userId, ApplyTypes.APPLY_EVENT);
        if (days > 14){
            throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH_EVENT);
        }

        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.APPLY_EVENT, reason);
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
