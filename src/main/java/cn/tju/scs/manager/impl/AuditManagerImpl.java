package cn.tju.scs.manager.impl;

import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.domain.factory.ApplyFactory;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.AuditManager;
import com.google.common.collect.Lists;
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

    @Resource
    UserDAO userDAO;

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
            UserDO param = new UserDO();
            param.setBossId(userId);
            List<UserDO> users = userDAO.selectUser(param);
            List<ApplyDO> list = Lists.newArrayList();
            for ( UserDO userDO : users ){
                applyDO.setUserId(userDO.getUserId());
                List<ApplyDO> applys = applyDAO.selectApplyDO(applyDO);
                list.addAll(applys);
            }
            return list;
        }catch ( DAOException e ){
            e.printStackTrace();
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
        }
    }
}
