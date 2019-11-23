package cc.adaoz.stop.watch;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author albert on 11/23/19
 */
public class StopWatchTest {

    public static void main(String[] args) {

        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        long maxValue = Long.MAX_VALUE;


        executor.execute(() -> test1(maxValue));
        executor.execute(() -> test2(maxValue));
        executor.execute(() -> test3(maxValue));
        executor.execute(() -> test4(maxValue));

    }

    public static void test1(long maxValue) {

        Stopwatch stopwatch = Stopwatch.createStarted();

        for (long i = 0L; i < maxValue>>>30; i++) {
            int l1 = 1 * 1;
        }
        stopwatch.stop();

        System.out.println("com.google.common.base.Stopwatch 耗时: " + stopwatch.elapsed(TimeUnit.NANOSECONDS)*10e-9);

    }

    public static void test2(long maxValue) {
        org.apache.commons.lang3.time.StopWatch stopWatchLang3 = StopWatch.createStarted();

        for (long i = 0L; i < maxValue>>>30; i++) {
            int l1 = 1 * 1;
        }

        stopWatchLang3.stop();

        System.out.println("org.apache.commons.lang3.time.StopWatch 耗时: " + stopWatchLang3.getNanoTime()*10e-9);

    }

    public static void test3(long maxValue) {
        org.springframework.util.StopWatch stopWatchSpring = new org.springframework.util.StopWatch();
        stopWatchSpring.start();

        for (long i = 0L; i < maxValue>>>30; i++) {
            int l1 = 1 * 1;
        }
        stopWatchSpring.stop();

        System.out.println("org.springframework.util.StopWatch 耗时: " + stopWatchSpring.getTotalTimeNanos()*10e-9);

    }

    public static void test4(long maxValue) {

        long l = System.nanoTime();
        for (long i = 0L; i < maxValue>>>30; i++) {
            int l1 = 1 * 1;
        }

        System.out.println("long l = System.nanoTime(); 耗时: " + (System.nanoTime() - l)*10e-9);

    }
}
