package threads;

import java.util.List;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MagicalListThreadWithSemaphore implements Runnable {

  private List<Integer> ints;
  private Semaphore semaphore;

  public MagicalListThreadWithSemaphore(final List<Integer> ints, final Semaphore semaphore) {
    this.ints = ints;
    this.semaphore = semaphore;
  }

  @Override
  public void run() {
    log.info("Waiting for someone to let me know when list is empty");
    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("List should be empty now " + ints.isEmpty());
  }
}
