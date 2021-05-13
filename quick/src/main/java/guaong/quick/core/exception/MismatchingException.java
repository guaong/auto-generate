package guaong.quick.core.exception;

/**
 * 不匹配
 * @author guaong
 */
public class MismatchingException extends RuntimeException {

    public MismatchingException() {
        super();
    }

    public MismatchingException(String message) {
        super(message);
    }

    public MismatchingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MismatchingException(Throwable cause) {
        super(cause);
    }

    protected MismatchingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
