package cn.tju.scs.dao;

import cn.tju.scs.domain.UserDO;
import java.util.List;
import cn.tju.scs.exception.DAOException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lichen.ll on 2016/8/28.
 */

@Repository
public interface UserDAO {

    List<UserDO> selectUser(UserDO userInfo) throws DAOException;

    void insertUser(UserDO userInfo) throws DAOException;

    void updateContactInfo(@Param("userId") Long userId, @Param("phone") String phone) throws DAOException;

    void updatePower(@Param("userId") Long userId, @Param("power") Integer power) throws DAOException;

    void deleteUser(@Param("userId") Long userId) throws DAOException;

}
