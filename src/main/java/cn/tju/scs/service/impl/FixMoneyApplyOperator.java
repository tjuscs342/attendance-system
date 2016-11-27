package cn.tju.scs.service.impl;

import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.AuditOperate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by yangwentao on 11/5/16.
 */
@Service("fixMoneyApplyOperator")
public class FixMoneyApplyOperator implements AuditOperate,ApplyOperate{
    @Resource
    AuditManager auditManager;

    @Resource
    ApplyManager applyManager;

    @Override
    public void auditPass(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId, AuditStatus.SUCCESS,remark,operatorId,operatorName);
    }

    @Override
    public void auditFail(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId,AuditStatus.FAIL,remark,operatorId,operatorName);
    }

    @Override
    public void doOperate(Long userId, Date startDate, Date endDate, String reason) throws BLLException {
        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.Apply_FixMoney, reason);
    }
}
