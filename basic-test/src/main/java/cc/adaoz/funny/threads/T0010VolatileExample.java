package cc.adaoz.funny.threads;


import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class T0010VolatileExample {

    private static boolean flag = false;
    private static int i = 0;

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(()->{

            try {
                TimeUnit.SECONDS.sleep(100);
                flag = true;
                System.out.println("flag is change to true ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (!flag) {
            i++;
            TimeUnit.SECONDS.sleep(100);
            System.out.printf("flag is %s%n", flag);
        }
        System.out.println("program run done ");

    }

}
