package cn.tju.scs.service.impl;

import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.AuditOperate;
import cn.tju.scs.util.DateUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by yangwentao on 11/3/16.
 */
@Service("withMaternityOperator")
public class WithMaternityOperator implements ApplyOperate,AuditOperate{
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
        int days = DateUtils.getDuration(startDate,endDate);
        //applyManager.clearUselessApply(userId, ApplyTypes.APPLY_WITHMATERNITY);
        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.APPLY_WITHMATERNITY, reason);
    }
}
