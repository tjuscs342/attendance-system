package cn.tju.scs.dao;

import cn.tju.scs.domain.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lichen.ll on 2016/8/28.
 */
@Repository
public interface UserDAO {

    UserDO selectUser ( @Param("userId") Integer userId );

}
