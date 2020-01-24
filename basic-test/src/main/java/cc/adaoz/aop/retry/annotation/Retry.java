package cc.adaoz.aop.retry.annotation;

import java.lang.annotation.*;


@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {


    /**
     * 最大失败尝试
     *
     * @return
     */
    int maxRetry() default 3;

    /**
     * 重试的间隔时间
     *
     * @return
     */
    long delay() default 0L;


    /**
     * 重试超时时间
     *
     * @return
     */
    long timeOut() default 60000L;

}
