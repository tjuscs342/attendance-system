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
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.AuditOperate;
import cn.tju.scs.util.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
<<<<<<< HEAD

=======
>>>>>>> lyj_new
@Service("yearApplyOperate")
public class YearApplyOperate implements ApplyOperate,AuditOperate {

    @Resource
    ApplyManager applyManager;

    @Resource
    AuditManager auditManager;

    @Override
    public void doOperate(Long userId, Date startDate, Date endDate, String reason) throws BLLException {

        int days = DateUtils.getDuration(startDate, endDate);
        applyManager.clearUselessApply(userId, ApplyTypes.APPLY_YEAR);
        List<ApplyDO> list = applyManager.selectApplysByType(userId, ApplyTypes.APPLY_YEAR);
        if (list == null)
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);

        int used = 0;
        for (ApplyDO item : list) {
<<<<<<< HEAD
            //出现非法的数据
            if( item.getResult() == null) continue;
=======
            if(item.getResult() == null )continue;
>>>>>>> lyj_new
            if (!DateUtils.checkUseless(item.getApplyDate()) && item.getResult().equals(AuditStatus.SUCCESS))
                used += DateUtils.getDuration(item.getStartDate(), item.getEndDate());
        }

        if (days + used > YearApplySalaryRule.DAYS_OF_YEAR) {
            throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH);
        }

        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.APPLY_YEAR, reason);
    }

    @Override
    public void auditPass(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId,AuditStatus.SUCCESS,remark,operatorId,operatorName);
    }

    @Override
    public void auditFail(String remark, Long operatorId, String operatorName, Long applicationId) throws BLLException {
        auditManager.auditApply(applicationId,AuditStatus.FAIL,remark,operatorId,operatorName);
    }
}
