package threads.sprinters;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnerThread implements Runnable {

  private final CountDownLatch countDownLatch;
  private final List<Long> positions;

  public RunnerThread(final CountDownLatch countDownLatch, final List<Long> positions) {
    this.countDownLatch = countDownLatch;
    this.positions = positions;
  }

  @Override
  public void run() {
    log.info("Preparing on starting line");
    countDownLatch.countDown();
    log.info(Thread.currentThread().getId() + " is ready and waiting for start");
    try {
      countDownLatch.await();
      //Thread.sleep(new Random().nextLong() % 11);
      //Thread.sleep(2000L);
    } catch (InterruptedException e) {
      log.warn("DUPA");
      e.printStackTrace();
    }

    finishRace();
  }

  private synchronized void finishRace() {
    log.info(Thread.currentThread().getId() + " finished the race");
    positions.add(Thread.currentThread().getId());
  }
}
