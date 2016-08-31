package cn.tju.scs.exception;

/**
 * Created by lichen.ll on 2016/8/30.
 */
public class DAOException extends Exception{

    private static final long serialVersionUID = 1L;

    public DAOException() {
        super();
    }

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
