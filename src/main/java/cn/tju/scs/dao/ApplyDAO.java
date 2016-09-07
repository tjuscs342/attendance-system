package cn.tju.scs.dao;

import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.DAOException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lichen.ll on 2016/9/7.
 */
@Repository
public interface ApplyDAO {

    List<ApplyDO> selectApplyDO ( ApplyDO applyDO ) throws DAOException;

    void insertApplyDO ( ApplyDO applyDO ) throws DAOException;

    void updateApplyDO ( ApplyDO applyDO ) throws DAOException;

    void deleteApplyDO ( @Param("applicationId")Long applicationId ) throws  DAOException;
}
