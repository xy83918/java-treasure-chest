package cc.adaoz.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2  * <pre>
 * 3  * xen虚拟机,5.5G内存;8核CPU
 * 4  * LOOP = 10000000
 * 5  * THREADS = 10
 * 6  * o:       45284
 * 7  * e:       205482
 * 8  * exte:    16731
 * 9  * </pre>
 * 10  *
 * 11  * k
 * 12  *
 * 13  * @author li.jinl 2010-7-9 上午09:16:14
 * 14
 */
public class NewExceptionTester {
    // 单次循环数量
    private static final int LOOP = 10000000;
    private static final ExecutorService POOL = Executors.newFixedThreadPool(30);
    // 并发线程数量
    private static final int THREADS = 10;
    private static final List<Long> newExceptionTimes = new ArrayList<Long>(THREADS);
    private static final List<Long> newExtExceptionTimes = new ArrayList<Long>(THREADS);
    private static final List<Long> newObjectTimes = new ArrayList<Long>(THREADS);

    public static void main(String[] args) throws Exception {
        List<Callable<Boolean>> all = new ArrayList<Callable<Boolean>>();
        all.addAll(tasks(new NewObject()));
        all.addAll(tasks(new NewException()));
        all.addAll(tasks(new NewExtException()));

        POOL.invokeAll(all);

        System.out.println("o:\t\t" + (total(newObjectTimes) / 1000000));
        System.out.println("e:\t\t" + (total(newExceptionTimes) / 1000000));
        System.out.println("exte:\t\t" + (total(newExtExceptionTimes) / 1000000));

        POOL.shutdown();
    }

    private static List<Callable<Boolean>> tasks(Callable<Boolean> c) {
        List<Callable<Boolean>> list = new ArrayList<Callable<Boolean>>(THREADS);
        for (int i = 0; i < THREADS; i++) {
            list.add(c);
        }
        return list;
    }

    private static long total(List<Long> list) {
        long sum = 0;
        for (Long v : list) {
            sum += v;
        }
        return sum;
    }

    public static class NewObject implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            long start = System.nanoTime();
            for (int i = 0; i < LOOP; i++) {
                new CustomObject("");
            }
            newObjectTimes.add(System.nanoTime() - start);
            return true;
        }

    }

    public static class NewException implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            long start = System.nanoTime();
            for (int i = 0; i < LOOP; i++) {
                new CustomException("");
            }
            newExceptionTimes.add(System.nanoTime() - start);
            return true;
        }

    }

    public static class NewExtException implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            long start = System.nanoTime();
            for (int i = 0; i < LOOP; i++) {
                new ExtCustomException("");
            }
            newExtExceptionTimes.add(System.nanoTime() - start);
            return true;
        }

    }

    /**
     * 100      * 自定义java对象.
     * 101      *
     * 102      * @author li.jinl 2010-7-9 上午11:28:27
     * 103
     */
    public static class CustomObject extends HashMap {

        private static final long serialVersionUID = 5176739397156548105L;

        private String message;

        public CustomObject(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    /**
     * 125      * 自定义普通的Exception对象
     * 126      *
     * 127      * @author li.jinl 2010-7-9 上午11:28:58
     * 128
     */
    public static class CustomException extends Exception {

        private static final long serialVersionUID = -6879298763723247455L;

        private String message;

        public CustomException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    /**
     * 150      * <pre>
     * 151      * 自定义改进的Exception对象 覆写了 fillInStackTrace方法
     * 152      * 1. 不填充stack
     * 153      * 2. 取消同步
     * 154      * </pre>
     * 155      *
     * 156      * @author li.jinl 2010-7-9 上午11:29:12
     * 157
     */
    public static class ExtCustomException extends Exception {

        private static final long serialVersionUID = -6879298763723247455L;

        private String message;

        public ExtCustomException(String message) {
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public Throwable fillInStackTrace() {
            return this;
        }
    }
}