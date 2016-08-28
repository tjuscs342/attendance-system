package cn.tju.scs.manager.impl;

import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.manager.UserManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lichen.ll on 2016/8/28.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserManagerImpl implements UserManager {

    @Resource
    UserDAO userDAO;

    public UserDO getUser(Integer userId) {
        try {
            return userDAO.selectUser(userId);
        }catch (Throwable e ){
            e.printStackTrace();
            return null;
        }
    }
}
