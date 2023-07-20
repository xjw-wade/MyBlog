package top.wade.exception;

/**
 * @Author xjw
 * @Date 2023/7/17 21:32
 * @Description: 非法请求异常
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
