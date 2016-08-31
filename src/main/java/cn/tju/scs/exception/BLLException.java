package cn.tju.scs.exception;

import cn.tju.scs.constant.ErrorEntity;
import lombok.Getter;

/**
 * 业务异常 Created by lichen.ll on 2016/8/30.
 */
public class BLLException extends Exception {
    private static final long serialVersionUID = 1L;

    @Getter
    protected final String    errorMessage;

    @Getter
    protected final String    errorCode;


    public BLLException(String errorMessage, String errorCode, Throwable t) {
        super(buildErrorEntity(errorMessage, errorCode), t);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BLLException(String errorMessage, String errorCode) {
        super(buildErrorEntity(errorMessage, errorCode));
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BLLException (ErrorEntity error ){
        super(buildErrorEntity(error.getErrorMessage(), error.getErrorCode()));
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
    }


    private static String buildErrorEntity(String errorMessage, String errorCode) {
        return "[" + errorCode + "]:" + errorMessage;
    }
}
