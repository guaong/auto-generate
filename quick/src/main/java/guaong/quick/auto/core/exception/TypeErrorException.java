package guaong.quick.auto.core.exception;

/**
 * 类型异常
 *
 * @author guaong
 * @version 1.0
 */
public class TypeErrorException extends RuntimeException{

    public TypeErrorException() {
    }

    public TypeErrorException(String message) {
        super(message);
    }

    public TypeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeErrorException(Throwable cause) {
        super(cause);
    }

    public TypeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
