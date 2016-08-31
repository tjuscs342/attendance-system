package cn.tju.scs.manager.impl;

import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.manager.UserManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;

/**
 * Created by lichen.ll on 2016/8/28.
 */

@Service
public class UserManagerImpl implements UserManager {

    @Resource
    UserDAO userDAO;
}
