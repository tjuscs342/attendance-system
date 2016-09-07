package cn.tju.scs.domain.factory;

import cn.tju.scs.domain.ApplyDO;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public class ApplyFactory {
    public static ApplyDO getEmptyApply ( ){
        ApplyDO applyDO = new ApplyDO();
        applyDO.setApplicationId(null);
        applyDO.setApplyDate(null);
        applyDO.setApplyType(null);
        applyDO.setAuditDate(null);
        applyDO.setEndDate(null);
        applyDO.setStartDate(null);
        applyDO.setOperatorId(null);
        applyDO.setOperatorName(null);
        applyDO.setUserId(null);
        return applyDO;
    }
}
