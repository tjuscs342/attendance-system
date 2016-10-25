package cn.tju.scs.validator;

import cn.tju.scs.constant.ApplyTypes;

/**
 * Created by lichen.ll on 2016/9/16.
 */
public class ApplyTypeValidator {

    public static boolean checkType ( Integer type ){
        if( type.equals(ApplyTypes.APPLY_YEAR))
            return true;
        else if( type.equals(ApplyTypes.APPLY_SICK))
            return true;
        return false;
    }
}
