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
        else if(type.equals(ApplyTypes.APPLY_EVENT))
            return true;
        else if(type.equals(ApplyTypes.APPLY_MARRY))
            return true;
        else if(type.equals(ApplyTypes.APPLY_MATERNITY))
            return true;
        else if(type.equals(ApplyTypes.APPLY_WITHMATERNITY))
            return true;
        else if(type.equals(ApplyTypes.Apply_FixMoney))
            return true;
        else if(type.equals(ApplyTypes.Apply_FixOverTime))
            return true;
        else if(type.equals(ApplyTypes.Apply_OverTime))
            return true;
        return false;
    }
}
