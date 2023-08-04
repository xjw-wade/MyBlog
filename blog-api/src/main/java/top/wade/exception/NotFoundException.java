package top.wade.exception;

/**
 * @Author xjw
 * @Date 2023/8/3 17:25
 * @Description: 404异常
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {

    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }



}
