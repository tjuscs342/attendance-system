package cn.tju.scs.manager;

import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;

import javax.xml.registry.infomodel.User;
import java.util.List;

/**
 * Created by lichen.ll on 2016/8/28.
 */
public interface UserManager {

    UserDO getUserInfoById ( Long userId ) throws BLLException;

    UserDO getUserInfoByName ( String userName ) throws BLLException;

    List<UserDO> getUserList() throws BLLException;

    List<UserDO> getUserListByBossId ( Long bossId) throws BLLException;
}
