package cn.tju.scs.constant;

/**
 * Created by lichen.ll on 2016/8/30.
 */
public class ErrorConstantColletion {

    public static class UserException {
        public final static ErrorEntity GET_USER_INFO_ERROR = new ErrorEntity("GET_USER_INFO_ERROR","获取用户信息失败");
        public final static ErrorEntity NULL_USER_ID_ERROR = new ErrorEntity("NULL_USER_ID_ERROR","请给出非空的用户Id");
        public final static ErrorEntity UN_EXITED_USER_ERROR = new ErrorEntity("UN_EXITED_USER_ERROR","该用户不存在");
        public final static ErrorEntity NULL_USER_NAME_ERROR = new ErrorEntity("NULL_USER_NAME","请给出非空的用户名");
    }

    public static class ApplyException {
        public final static ErrorEntity GET_APPLY_INFO_ERROR = new ErrorEntity("GET_APPLY_INFO_ERROR","获取申请信息失败");
    }

    public static class ApplyRuleException{
        public final static ErrorEntity APPLY_TOO_MUCH = new ErrorEntity("APPLY_TOO_MUCH","没有那么多可请的年假");
    }

    public final static ErrorEntity SYSTEM_ERROR = new ErrorEntity("SYSTEM_ERROR","系统繁忙");
}
