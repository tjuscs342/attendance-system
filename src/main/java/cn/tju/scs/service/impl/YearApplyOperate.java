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

import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
@Service("yearApplyOperate")
public class YearApplyOperate implements ApplyOperate,AuditOperate {

    @Resource
    ApplyManager applyManager;

    @Resource
    AuditManager auditManager;

    @Override
    public void doOperate(Long userId, Date startDate, Date endDate, String reason) throws BLLException {
        Calendar startC = Calendar.getInstance();
        Calendar endC = Calendar.getInstance();
        startC.setTime(startDate);
        endC.setTime(endDate);

        int days = DateUtils.getDuration(startDate, endDate);
        int x =endC.get(Calendar.YEAR) - startC.get(Calendar.YEAR);
        System.out.print(x);
        Date endDate1;
        Date startDate1;
        List<ApplyDO> list = applyManager.selectApplysByType(userId, ApplyTypes.APPLY_YEAR);
        if (list == null)
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
        if (x > 1){
            throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH);

        }
        else if (x == 1){
            endDate1 = DateUtils.splitDate(startDate,endDate,0);
            startDate1 = DateUtils.splitDate(startDate,endDate,1);
            int days1 = DateUtils.getDuration(startDate,endDate1);
            int days2 = DateUtils.getDuration(startDate1,endDate);
            int used1 = 0;
            int used2 = 0;
            for (ApplyDO item : list) {
                //出现非法的数据
                if(item.getResult() == null) continue;
                if (!DateUtils.checkUseless(item.getStartDate(),startDate) && item.getResult().equals(AuditStatus.SUCCESS))
                    used1 += DateUtils.getDuration(item.getStartDate(), item.getEndDate());
            }
            for (ApplyDO item : list) {
                //出现非法的数据
                if( item.getResult() == null) continue;
                if (!DateUtils.checkUseless(item.getEndDate(),endDate) && item.getResult().equals(AuditStatus.SUCCESS))
                    used2 += DateUtils.getDuration(item.getStartDate(), item.getEndDate());
            }
            if (days1 + used1 > YearApplySalaryRule.DAYS_OF_YEAR){
                throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH);
            }
            if (days2 + used2 > YearApplySalaryRule.DAYS_OF_YEAR){
                throw Exceptions.newBLLException(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH);
            }
            applyManager.applyByType(userId, startDate, endDate1, ApplyTypes.APPLY_YEAR, reason);
            applyManager.applyByType(userId, startDate1, endDate, ApplyTypes.APPLY_YEAR, reason);

            return;
        }
//        applyManager.clearUselessApply(userId, ApplyTypes.APPLY_YEAR);



        int used = 0;

        for (ApplyDO item : list) {
            //出现非法的数据
            if( item.getResult() == null) continue;
            if (!DateUtils.checkUseless(item.getStartDate(),startDate) && item.getResult().equals(AuditStatus.SUCCESS))
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
