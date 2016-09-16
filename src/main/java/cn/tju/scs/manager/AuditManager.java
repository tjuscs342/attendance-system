package cn.tju.scs.manager;

import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.exception.BLLException;
import java.util.List;

/**
 * Created by lichen.ll on 2016/9/16.
 */
public interface AuditManager {

    void auditApply ( long applicationId, int status , String remark , long operatorId , String operatorName  ) throws BLLException;

    List<ApplyDO>  selectApplys ( long userId ) throws BLLException;
}
