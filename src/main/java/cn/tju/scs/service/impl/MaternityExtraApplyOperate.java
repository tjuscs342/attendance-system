package cn.tju.scs.service.impl;

import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.AuditOperate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liu on 16-10-30.
 */
@Service("MaternityExtraApplyOperate")
public class MaternityExtraApplyOperate implements ApplyOperate,AuditOperate {
    @Resource
    ApplyManager applyManager;
    @Resource
    AuditManager auditManager;
    @Override
    public void doOperate(Long userId, Date startDate, Date endDate, String reason) throws BLLException{
        List<ApplyDO> list = applyManager.selectApplysByType(userId,ApplyTypes.APPLY_MATERNITY);
        if (list == null)
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
//        if ()
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
