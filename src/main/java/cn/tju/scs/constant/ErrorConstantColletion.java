package cn.tju.scs.constant;

/**
 * Created by lichen.ll on 2016/8/30.
 */
public class ErrorConstantColletion {

    public static class UserException {
        public final static ErrorEntity GET_USER_INFO_ERROR = new ErrorEntity("GET_USER_INFO_ERROR","获取用户信息失败");
        public final static ErrorEntity NULL_USER_ID_ERROR = new ErrorEntity("NULL_USER_ID_ERROR","请给出非空的用户Id");
        public final static ErrorEntity NULL_USER_NAME_ERROR = new ErrorEntity("NULL_USER_NAME","请给出非空的用户名");
    }
}
