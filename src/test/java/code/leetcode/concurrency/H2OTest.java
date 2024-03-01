package code.leetcode.concurrency;

import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

class H2OTest {

    class H2O {

        // Semaphores to control the release of hydrogen and oxygen atoms.
        private Semaphore semaphoreHydrogen = new Semaphore(
                2); // hydrogen semaphore initialized to 2 permits, since we need 2 H for every O
        private Semaphore semaphoreOxygen = new Semaphore(
                0); // oxygen semaphore initialized with 0 permits, will be released by hydrogen

        public H2O() {
            // Constructor for H2O, nothing needed here since semaphores are initialized above
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            // Acquire a permit for releasing a hydrogen atom
            semaphoreHydrogen.acquire();
            // releaseHydrogen.run() outputs "H"
            releaseHydrogen.run();
            // Release a permit for oxygen, signaling that one H has been released
            semaphoreOxygen.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            // Acquire two permits for releasing an oxygen atom as we need two hydrogen atoms before releasing one oxygen atom
            semaphoreOxygen.acquire(2);
            // releaseOxygen.run() outputs "O"
            releaseOxygen.run();
            // Release two permits for hydrogen, allowing the release of two hydrogen atoms
            semaphoreHydrogen.release(2);
        }
    }

    @Test
    void test() throws Exception {
        for (int i = 0; i < 1; i++) {
            runTest();
        }
    }

    private void runTest() throws InterruptedException {
        H2O printer = new H2O();
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    //                    System.out.println("H loop " + i);
                    printer.hydrogen(() -> System.out.print("H"));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    //                    System.out.println("O loop " + i);
                    printer.oxygen(() -> System.out.print("O"));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.start();
        t1.start();

        t1.join();
        t2.join();
        System.out.println();
    }
}

