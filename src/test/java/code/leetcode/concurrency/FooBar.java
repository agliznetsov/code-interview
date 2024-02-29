package code.leetcode.concurrency;

import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

class FooBarTest {

    class FooBar {
        private final int loopCount; // The number of times "foo" and "bar" should be printed.
        private final Semaphore fooSemaphore = new Semaphore(1); // A semaphore for "foo", allowing "foo" to print first.
        private final Semaphore barSemaphore = new Semaphore(0); // A semaphore for "bar", initially locked until "foo" is printed.

        public FooBar(int n) {
            this.loopCount = n;
        }

        // The method for printing "foo"
        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < loopCount; i++) {
                fooSemaphore.acquire(); // Acquire a permit before printing "foo", ensuring "foo" has the turn to print
                printFoo.run();         // Output "foo"
                barSemaphore.release(); // Release a permit for "bar" after "foo" is printed, allowing "bar" to print next
            }
        }

        // The method for printing "bar"
        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < loopCount; i++) {
                barSemaphore.acquire(); // Acquire a permit before printing "bar", ensuring "bar" has the turn to print
                printBar.run();         // Output "bar"
                fooSemaphore.release(); // Release a permit for "foo" after "bar" is printed, allowing "foo" to print next
            }
        }
    }

    @Test
    void test() throws Exception {
        for (int i = 0; i < 5; i++) {
            runTest();
        }
    }

    @Test
    void semaphoreTest() throws Exception{
        Semaphore semaphore = new Semaphore(0);
        semaphore.release(2);
        semaphore.acquire();
        System.out.println("acquire 1");
        semaphore.acquire();
        System.out.println("acquire 2");
    }

    private void runTest() throws InterruptedException {
        FooBar fooBar = new FooBar(20);
        Thread t1 = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("."));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("|"));
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

