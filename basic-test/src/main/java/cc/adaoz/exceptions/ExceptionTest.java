package cc.adaoz.exceptions;


public class ExceptionTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            doExTest();
        }

    }

    private static void doExTest() {
        long start = System.nanoTime();
        for (int i = 0; i < 100000; ++i) {
            try {
                throw new RuntimeException("" + Math.random());
            } catch (Exception e) {
                System.out.println("fuck " + e);
            }
        }
        System.out.println("time: " + (System.nanoTime() - start));
    }
}