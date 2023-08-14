package top.wade.exception;

/**
 * @Author xjw
 * @Date 2023/8/10 16:48
 * @Description: 持久化异常
 */
public class PersistenceException extends RuntimeException {
    public PersistenceException() {
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
