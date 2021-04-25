package guaong.quick.auto.core.exception;

/**
 * 写入失败异常
 *
 * @author guaong
 * @version 1.0
 */
public class WriteFailException extends RuntimeException {

    public WriteFailException() {
    }

    public WriteFailException(String message) {
        super(message);
    }

    public WriteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriteFailException(Throwable cause) {
        super(cause);
    }

    public WriteFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
