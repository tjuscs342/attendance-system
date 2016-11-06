package cn.tju.scs.manager.impl;

import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.UserManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lichen.ll on 2016/8/28.
 */

@Service
public class UserManagerImpl implements UserManager {

    Logger  logger = Logger.getLogger(this.getClass());

    @Resource
    UserDAO userDAO;

    @Override
    public UserDO getUserInfoById(Long userId) throws BLLException {

        // 参数校验
        if (userId == null) {
            logger.error(ErrorConstantColletion.UserException.NULL_USER_NAME_ERROR);
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.NULL_USER_ID_ERROR);
        }
        // 业务逻辑
        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        try {
            List<UserDO> list = userDAO.selectUser(userDO);
            return list==null||list.size()==0?null:list.get(0);
        } catch (DAOException e) {
            logger.error(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR, e);
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR);
        }
    }

    @Override
    public UserDO getUserInfoByName(String userName) throws BLLException {
        // 参数校验
        if (userName == null) {
            logger.error(ErrorConstantColletion.UserException.NULL_USER_NAME_ERROR);
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.NULL_USER_NAME_ERROR);
        }
        // 业务逻辑
        UserDO userDO = new UserDO();
        userDO.setUserName(userName);
        try {
            List<UserDO> list = userDAO.selectUser(userDO);
            return list==null||list.size()==0?null:list.get(0);
        } catch (DAOException e) {
            logger.error(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR, e);
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR);
        }
    }

    @Override
    public List<UserDO> getUserList() throws BLLException {
        try {
            return userDAO.selectUser(new UserDO());
        } catch (DAOException e) {
            logger.error(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR, e);
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR);
        }
    }

    @Override
    public List<UserDO> getUserListByBossId( Long bossId)throws BLLException{
        UserDO userDO = new UserDO();
        userDO.setBossId(bossId);
        try{
            List<UserDO> list = userDAO.selectUser(userDO);
            return list;
        } catch (DAOException e){
            logger.error(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR, e);
            throw Exceptions.newBLLException(ErrorConstantColletion.UserException.GET_USER_INFO_ERROR);
        }
    }
}
