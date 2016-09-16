package cn.tju.scs.manager;

import cn.tju.scs.exception.BLLException;

/**
 * Created by lichen.ll on 2016/9/16.
 */
public interface AuditManager {

    void auditApply ( long applicationId, int status , String remark , long operatorId , String operatorName  ) throws BLLException;
}
