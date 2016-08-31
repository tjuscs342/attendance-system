package cn.tju.scs.manager.impl;

import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.manager.UserManager;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by lichen.ll on 2016/8/28.
 */

@Service
public class UserManagerImpl implements UserManager {

    @Resource
    UserDAO userDAO;
}
