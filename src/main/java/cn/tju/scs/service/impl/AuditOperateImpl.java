package cn.tju.scs.service.impl;

import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.service.AuditOperate;

import javax.annotation.Resource;

/**
 * Created by lichen.ll on 2016/9/8.
 */
public class AuditOperateImpl implements AuditOperate {

    @Resource
    ApplyDAO applyDAO;


    @Override
    public void auditPass( String remark, Long operatorId, String operatorName , Long applicationId ) throws BLLException{
        audit(AuditStatus.SUCCESS , remark , operatorId , operatorName , applicationId);
    }

    @Override
    public void auditFail(String remark, Long operatorId, String operatorName , Long applicationId ) throws BLLException {
        audit(AuditStatus.FAIL, remark , operatorId , operatorName , applicationId );
    }

    private void audit ( Integer result , String remark , Long operatorId , String operatorName , Long applicationId )
    throws BLLException {
        ApplyDO applyDO =  new ApplyDO();
        applyDO.setResult(result);
        applyDO.setRemark(remark);
        applyDO.setOperatorId(operatorId);
        applyDO.setOperatorName(operatorName);
        applyDO.setApplicationId(applicationId);
        try {
            applyDAO.updateApplyDO(applyDO);
        }catch( DAOException e ){
            e.printStackTrace();
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
        }
    }
}
