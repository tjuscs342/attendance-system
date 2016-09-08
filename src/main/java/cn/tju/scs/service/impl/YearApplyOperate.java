package cn.tju.scs.service.impl;

import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.util.DateUtils;
import java.util.List;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public class YearApplyOperate implements ApplyOperate {

    @Resource
    ApplyManager applyManager;

    @Override
    public void doOperate(Long userId, Date startDate, Date endDate, String reason) throws BLLException {

        int days = DateUtils.getDuration(startDate, endDate);
        applyManager.clearUselessApply(userId, ApplyTypes.APPLY_YEAR);
        List<ApplyDO> list = applyManager.selectApplysByType(userId, ApplyTypes.APPLY_YEAR);
        if (list == null)
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);

        int used = 0;
        for (ApplyDO item : list) {
            if (!DateUtils.checkUseless(item.getApplyDate()) && item.getResult().equals(AuditStatus.SUCCESS))
                used += DateUtils.getDuration(item.getStartDate(), item.getEndDate());
        }

        if (days + used > YearApplySalaryRule.DAYS_OF_YEAR) {
            throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH);
        }

        applyManager.applyByType(userId, startDate, endDate, ApplyTypes.APPLY_YEAR, reason);
    }
}
