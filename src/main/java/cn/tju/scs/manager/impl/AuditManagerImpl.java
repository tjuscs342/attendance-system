package cn.tju.scs.manager.impl;

import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.factory.ApplyFactory;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.AuditManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lichen.ll on 2016/9/16.
 */
@Service
public class AuditManagerImpl implements AuditManager{

    @Resource
    ApplyDAO applyDAO;

    @Override
    public void auditApply(long applicationId, int status , String remark , long operatorId , String operatorName ) throws BLLException {
        ApplyDO param = ApplyFactory.getEmptyApply();
        param.setApplicationId(applicationId);
        param.setResult(status);
        param.setOperatorId(operatorId);
        param.setRemark(remark);
        param.setOperatorName(operatorName);
        try {
            applyDAO.updateApplyDO(param);
        }catch ( DAOException e ){
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
        }
    }

    @Override
    public List<ApplyDO> selectApplys(long userId) throws BLLException {
        try {
            ApplyDO applyDO = ApplyFactory.getEmptyApply();
            applyDO.setOperatorId(userId);
            return applyDAO.selectApplyDO(applyDO);
        }catch ( DAOException e ){
            e.printStackTrace();
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
        }
    }
}
