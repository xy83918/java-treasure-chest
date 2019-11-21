package cc.adaoz.time;

/**
 * @author albert on 11/21/19
 */
public class StatisticsTest {
    public static void main(String[] args) {
        int times= Integer.MAX_VALUE;

        long start = System.currentTimeMillis();
        for (long i = 0; i < times; i++) {
            SystemClock.millisClock().now();
        }
        long end = System.currentTimeMillis();

        System.out.println("SystemClock Time:" + (end - start) + "毫秒");

        long start2 = System.currentTimeMillis();
        for (long i = 0; i < times; i++) {
            System.currentTimeMillis();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("SystemCurrentTimeMillis Time:" + (end2 - start2) + "毫秒");
    }

}
