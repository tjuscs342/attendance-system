package cn.tju.scs.exception;

import cn.tju.scs.constant.ErrorEntity;

/**
 * Created by lichen.ll on 2016/8/31.
 */
public class Exceptions {

    public static BLLException newBLLException (ErrorEntity errorEntity ){
        return new BLLException( errorEntity );
    }
}
