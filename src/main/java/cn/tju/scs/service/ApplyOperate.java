package cn.tju.scs.service;

import cn.tju.scs.exception.BLLException;

import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public interface ApplyOperate {

    void doOperate ( Long userId , Date startDate , Date endDate , String reason )
            throws BLLException;
}
