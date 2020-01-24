package cc.adaoz.aop.retry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 *
 * @author albert
 * @since 1/17/20 9:35 PM
 * @version 0.1
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BizException extends Exception {

    private Object carrier;
    /**
     * 异常对应的返回码
     */
    private Integer code;
    /**
     * 异常对应的描述信息
     */
    private String msg;

    public BizException() {
        super("", null, false, false);
    }

    public BizException(String msg) {
        super(msg, (Throwable) null, false, false);
        this.msg = msg;
    }

    public BizException(String msg, boolean trace) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = message;
    }

    public BizException(Integer code, String msg) {
        super(msg, (Throwable) null, false, false);
        this.code = code;
        this.msg = msg;
    }

    public BizException(Integer code, String msg, boolean trace) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
