package cc.adaoz.aop.retry.aspect;

import cc.adaoz.aop.retry.BizException;
import cc.adaoz.aop.retry.annotation.Retry;
import cc.adaoz.time.SystemClock;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Retry can retry 3 times
 *
 * @author albert
 * @version 0.1
 * @since 1/17/20 5:06 PM
 */
@Aspect
@Slf4j
public class RetryAspect {


    @Around("@annotation(com.dt.framework.retry.annotation.Retry)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        Method method = getMethod(joinPoint);
        Retry retry = method.getAnnotation(Retry.class);

        long delay = retry.delay();
        long timeOut = retry.timeOut();
        long timeOutAt = SystemClock.millisClock().now() + timeOut;

        int maxRetry = retry.maxRetry();
        if (maxRetry <= 1) {
            return joinPoint.proceed();
        }

        int alreadyTry = 0;
        BizException retryTimeOut = new BizException("");
        try {
            while (alreadyTry++ < maxRetry) {
                try {
                    Thread.sleep(delay);
                    if (SystemClock.millisClock().now() > timeOutAt) {
                        retryTimeOut.setMsg("retry Time Out");
                        break;
                    }
                    return joinPoint.proceed();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    log.info("already try {} times,exception is ", alreadyTry, e);
                }
            }
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
            throwable.printStackTrace();
            log.error(throwable.getMessage(), throwable);
        }

        retryTimeOut.setCarrier(recordError(joinPoint));

        if (alreadyTry == maxRetry) {
            retryTimeOut.setMsg("Exceeded maximum retries");
        }
        throw retryTimeOut;

    }

    private Map recordError(ProceedingJoinPoint joinPoint) {

        Builder messageBuilder = ImmutableMap.<String, Object>builder();

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        messageBuilder.put("className", className);
        messageBuilder.put("methodName", methodName);

        Builder requestParameterBuilder = ImmutableMap.<String, String>builder();

        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < paramNames.length; i++) {
            requestParameterBuilder.put(paramNames[i], paramValues[i] != null ? paramValues[i].toString() : "");
        }

        ImmutableMap requestParameter = requestParameterBuilder.build();

        messageBuilder.put("requestParameter", requestParameter);

        ImmutableMap message = messageBuilder.build();

        System.out.println(message);

        return message;

    }

    public Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("This annotation can only be used on methods");
        }
        msig = (MethodSignature) sig;
        Object target = pjp.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        return currentMethod;
    }
}
