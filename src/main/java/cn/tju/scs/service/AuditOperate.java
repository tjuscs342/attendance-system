package cn.tju.scs.service;

import cn.tju.scs.exception.BLLException;

/**
 * Created by lichen.ll on 2016/9/8.
 */
public interface AuditOperate {

    void auditPass ( String remark , Long operatorId , String operatorName , Long applicationId ) throws BLLException;

    void auditFail ( String remark , Long operatorId , String operatorName , Long applicationId ) throws BLLException;
}
