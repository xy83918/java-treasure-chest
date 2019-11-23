package cc.adaoz.bean.copy;


import com.google.common.base.Stopwatch;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author albert on 11/21/19
 */
public class TestBeanCopy {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        final OriginObject originObject = new OriginObject();
        originObject.setS0("setS0");
        originObject.setS1("setS0");
        originObject.setS2("setS0");
        originObject.setS3("setS0");
        originObject.setS4("setS0");
        originObject.setS5("setS0");
        originObject.setS6("setS0");
        originObject.setS7("setS0");
        originObject.setS8("setS0");
        originObject.setS9("setS0");
        originObject.setS10("setS0");
        originObject.setS11("setS0");
        originObject.setS12("setS0");
        originObject.setS13("setS0");
        originObject.setS14("setS0");
        originObject.setS15("setS0");
        originObject.setS16("setS0");
        originObject.setS17("setS0");
        originObject.setS18("setS0");
        originObject.setS19("setS0");
        originObject.setS20("setS0");
        originObject.setS21("setS0");
        originObject.setS22("setS0");
        originObject.setS23("setS0");
        originObject.setS24("setS0");
        originObject.setS25("setS0");
        originObject.setS26("setS0");
        originObject.setS27("setS0");
        originObject.setS28("setS0");
        originObject.setS29("setS0");

        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        final int len = 10000000;
        executor.execute(() -> {
            try {
                testApacheBeanUtils(originObject, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                testCglibBeanCopier(originObject, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                testApacheBeanUtilsPropertyUtils(originObject, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                testSpringFramework(originObject, len);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }

    private static void testCglibBeanCopier(OriginObject origin, int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================cglib BeanCopier执行" + len + "次================");
        DestinationObject destination3 = new DestinationObject();

        for (int i = 0; i < len; i++) {
            BeanCopier copier = BeanCopier.create(OriginObject.class, DestinationObject.class, false);
            copier.copy(origin, destination3, null);
        }
        stopwatch.stop();

        System.out.println("testCglibBeanCopier 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testApacheBeanUtils(OriginObject origin, int len)
            throws IllegalAccessException, InvocationTargetException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================apache BeanUtils执行" + len + "次================");
        DestinationObject destination2 = new DestinationObject();
        for (int i = 0; i < len; i++) {
            BeanUtils.copyProperties(destination2, origin);
        }

        stopwatch.stop();

        System.out.println("testApacheBeanUtils 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testSpringFramework(OriginObject origin, int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("================springframework执行" + len + "次================");
        DestinationObject destination = new DestinationObject();

        for (int i = 0; i < len; i++) {
            org.springframework.beans.BeanUtils.copyProperties(origin, destination);
        }

        stopwatch.stop();

        System.out.println("testSpringFramework 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testApacheBeanUtilsPropertyUtils(OriginObject origin, int len)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        org.apache.commons.lang3.time.StopWatch stopWatchLang3 = StopWatch.createStarted();
        org.springframework.util.StopWatch stopWatchSpring = new org.springframework.util.StopWatch();
        stopWatchSpring.start();
        System.out.println();
        System.out.println("================apache BeanUtils PropertyUtils执行" + len + "次================");
        DestinationObject destination2 = new DestinationObject();
        for (int i = 0; i < len; i++) {
            PropertyUtils.copyProperties(destination2, origin);
        }

        stopwatch.stop();
        stopWatchLang3.stop();
        System.out.println(stopWatchLang3.getNanoTime());
        System.out.println(stopWatchSpring.prettyPrint());
        System.out.println("testApacheBeanUtilsPropertyUtils 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
