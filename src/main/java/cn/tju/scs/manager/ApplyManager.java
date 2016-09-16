package cn.tju.scs.manager;

import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.BLLException;

import java.util.Date;
import java.util.List;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public interface ApplyManager {

    List<ApplyDO> selectApplysByType ( Long userId , Integer applyType ) throws BLLException;

    List<ApplyDO> selectApplys ( Long userId ) throws BLLException;

    void clearUselessApply ( Long userId , Integer applyType ) throws BLLException;

    void applyByType (Long userId , Date start , Date end , Integer applyType , String reason ) throws BLLException;

    void updateApplyInfo ( Long applicationId , Integer type , Date start , Date end ) throws BLLException;
}
