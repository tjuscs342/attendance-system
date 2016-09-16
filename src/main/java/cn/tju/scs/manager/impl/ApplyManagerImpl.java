package cn.tju.scs.manager.impl;

import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.domain.factory.ApplyFactory;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.util.DateUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lichen.ll on 2016/9/7.
 */
@Service
public class ApplyManagerImpl implements ApplyManager {

    @Resource
    ApplyDAO applyDAO;

    @Resource
    UserDAO  userDAO;

    @Override
    public List<ApplyDO> selectApplysByType(Long userId, Integer applyType) throws BLLException {
        if (userId == null)
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.NULL_USER_ID_ERROR);
        if (applyType == null)
            return Lists.newArrayList();

        try {
            UserDO userDO = new UserDO();
            userDO.setUserId(userId);
            List list = userDAO.selectUser(userDO);
            if (list == null || list.size() == 0)
                throw Exceptions.newBLLException(ErrorConstantColletion.UserException.UN_EXITED_USER_ERROR);
        } catch (DAOException e) {
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
        }
        ApplyDO applyDO = ApplyFactory.getEmptyApply();
        applyDO.setUserId(userId);
        applyDO.setApplyType(applyType);
        try{
            return applyDAO.selectApplyDO( applyDO );
        }catch ( DAOException e ){
            throw Exceptions.newBLLException(ErrorConstantColletion.ApplyException.GET_APPLY_INFO_ERROR);
        }
    }

    @Override
    public void clearUselessApply(Long userId, Integer applyType) throws BLLException {
        List<ApplyDO> list = this.selectApplysByType(userId, applyType);
        for (ApplyDO applyDO : list) {
            if(!DateUtils.checkUseless(applyDO.getApplyDate())) continue;
            try{
                applyDAO.deleteApplyDO(applyDO.getApplicationId());
            }catch ( DAOException e ){
                throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
            }
        }
    }

    @Override
    public void applyByType(Long userId, Date start, Date end, Integer applyType , String reason ) throws BLLException {
        ApplyDO applyDO = ApplyFactory.getEmptyApply();
        applyDO.setApplyType(applyType);
        applyDO.setReason(reason);
        applyDO.setStartDate(start);
        applyDO.setEndDate(end);
        applyDO.setUserId(userId);
        applyDO.setResult(AuditStatus.PENDING);
        try {
            applyDAO.insertApplyDO(applyDO);
        }catch ( DAOException e ){
            e.printStackTrace();
            throw Exceptions.newBLLException(ErrorConstantColletion.SYSTEM_ERROR);
        }
    }

}
