package cn.tju.scs.constant;

import lombok.Data;

/**
 * Created by lichen.ll on 2016/8/30.
 */

@Data
public class ErrorEntity {
    private String errorCode;
    private String errorMessage;

    public ErrorEntity() {
        this.errorCode = "UNKNOWN_ERROR";
        this.errorMessage = "未知错误";
    }

    public ErrorEntity( String errorCode , String errorMessage ){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
