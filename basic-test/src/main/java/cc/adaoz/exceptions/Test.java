package cc.adaoz.exceptions;

public class Test {
    public static void main(String[] args) {
        int a = 0, b = 2;
        long startTime = System.nanoTime();
        for (int j = 0; j < 1000; j++) {
            for (int i = 10; i >= 0; i--) {
                try {
                    a = b / i;
                } catch (Exception e) {
                    a = 1;
                } finally {

                }
            }
        }
        long runTime = System.nanoTime() - startTime;
        System.out.println(runTime);
    }
}