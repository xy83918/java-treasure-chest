package cc.adaoz.time;

import com.google.common.base.Stopwatch;

import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author albert on 11/21/19
 */
public class StatisticsTest {
    public static void main(String[] args) {
        int times= Integer.MAX_VALUE;

        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        executor.execute(() -> testSystemCurrentTimeMillis(times));
        executor.execute(() -> testSystemClockMillisClockNow(times));
        executor.execute(() -> testInstantNowGetEpochSecond(times));

    }


    private static void testSystemCurrentTimeMillis(int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================SystemCurrentTimeMillis ==执行: " + len + "次================");

        for (int i = 0; i < len; i++) {
            System.currentTimeMillis();
        }
        stopwatch.stop();

        System.out.println("testSystemCurrentTimeMillis 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testSystemClockMillisClockNow(int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================ SystemClock.millisClock().now() ==执行: " + len + "次================");

        for (int i = 0; i < len; i++) {
            SystemClock.millisClock().now();
        }
        stopwatch.stop();

        System.out.println("testSystemClockMillisClockNow 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testInstantNowGetEpochSecond(int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================ Instant.now().getEpochSecond() ==执行: " + len + "次================");

        for (int i = 0; i < len; i++) {
            Instant.now().getEpochSecond();
        }
        stopwatch.stop();

        System.out.println("testSystemClockMillisClockNow 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}
