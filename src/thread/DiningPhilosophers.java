package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liudongyang
 * @date 2020/05/21
 */
public class DiningPhilosophers {
    Semaphore[] semaphore = null;
    ReentrantLock reentrantLock = null;

    public DiningPhilosophers() {
        semaphore = new Semaphore[]{
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1),
                new Semaphore(1)
        };
        reentrantLock = new ReentrantLock();
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftForkIndex = philosopher;
        int rightForkIndex = (philosopher + 1) % 5;

        // 偶数先拿左边
        if ((philosopher & 1) == 0) {
            semaphore[leftForkIndex].acquire();
            semaphore[rightForkIndex].acquire();

        } else {
            semaphore[rightForkIndex].acquire();
            semaphore[leftForkIndex].acquire();
        }

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();

        semaphore[leftForkIndex].release();
        semaphore[rightForkIndex].release();


    }
}
