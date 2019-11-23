package cc.adaoz.number;

import cc.adaoz.bean.copy.DestinationObject;
import com.google.common.base.Stopwatch;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author albert on 11/22/19
 */
public class NumberTest {

    public static void main(String[] args) {

        Integer i1 = 1;
        Integer i2 = 2;
        Long l1 = Long.MAX_VALUE;
        Long l2 = 2L;

        int len = Integer.MAX_VALUE;
        long i = (Long.valueOf(i1) << 32) + len;
        System.out.println(Long.toHexString(i));
        System.out.println(Long.toHexString(i >>> 32));
        System.out.println(Long.toHexString(l1 >>> 32));

        System.out.println(Long.toHexString(l2));

        Integer numb = -len;
        Integer numb2 = len;
        System.out.println(Integer.toHexString(numb));
        System.out.println(Integer.toHexString((numb & 0xffff0000) >> 16));
        System.out.println(Integer.toHexString((numb & 0xffff0000) >>> 16));
        int i3 = (numb) >> 16;
        String x = Integer.toHexString(i3);
        System.out.println(i3);
        System.out.println(Integer.toHexString((numb) >>> 16));
        System.out.println(Integer.toHexString((numb2) >> 16));
        System.out.println(Integer.toHexString((numb2) >>> 16));


        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        executor.execute(() -> test1(len));
        executor.execute(() -> test2(len));
        executor.execute(() -> test3(len));
        executor.execute(() -> test4(len));

    }


    private static void test1(int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================ l / 2 执行" + len + "次================");
        DestinationObject destination3 = new DestinationObject();

        for (int i = 0; i < len; i++) {
            int l1 = len / 2;
        }
        stopwatch.stop();

        System.out.println("l / 2 执行 耗时: " + stopwatch.elapsed(TimeUnit.NANOSECONDS));
    }

    private static void test2(int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================ l >>> 1 执行" + len + "次================");

        for (int i = 0; i < len; i++) {
            int l1 = len >>> 1;
        }
        stopwatch.stop();

        System.out.println("l >>> 1 耗时: " + stopwatch.elapsed(TimeUnit.NANOSECONDS));
    }

    private static void test4(int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================ len >>> len 执行" + 1 + "次================");

        int l1 = len >>> len;
        stopwatch.stop();

        System.out.println("len >>> len 耗时: " + stopwatch.elapsed(TimeUnit.NANOSECONDS));
    }

    private static void test3(int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================ len / 2 / len 执行" + 1 + "次================");

        int l1 = len / 2 / len;

        stopwatch.stop();

        System.out.println("len / 2 / len 耗时: " + stopwatch.elapsed(TimeUnit.NANOSECONDS));
    }

}
