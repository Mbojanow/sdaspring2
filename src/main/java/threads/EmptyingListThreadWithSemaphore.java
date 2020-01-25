package threads;

import java.util.List;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyingListThreadWithSemaphore implements Runnable {

  private List<Integer> ints;
  private Semaphore semaphore;

  public EmptyingListThreadWithSemaphore(final List<Integer> ints, final Semaphore semaphore) {
    this.ints = ints;
    this.semaphore = semaphore;
  }

  private synchronized void clearList() {
    while(!ints.isEmpty()) {
      ints.remove(0);
    }
    try {
      Thread.sleep(2000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    log.info("Starting to empty list");
    clearList();
    semaphore.release();
    log.info("List is cleared");
  }
}
