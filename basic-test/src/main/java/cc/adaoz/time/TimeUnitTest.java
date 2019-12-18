package cc.adaoz.time;

import java.util.concurrent.TimeUnit;

/**
 * @author albert on 12/11/19
 */
public class TimeUnitTest {
    public static void main(String[] args) {

        System.out.println(TimeUnit.SECONDS.toMillis(2));

        System.out.println(SystemClock.millisClock().now());
    }
}
