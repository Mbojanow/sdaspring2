package threads;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MagicalListThread implements Runnable {

  private final List<Integer> ints;

  public MagicalListThread(final List<Integer> ints) {
    this.ints = ints;
  }

  public synchronized boolean isListEmpty() {
    return ints.isEmpty();
  }

  @Override
  public void run() {
//    getInts().isEmpty(); -> passe
    log.info("Checking if list is empty");
    while (!isListEmpty()) {

    }
    log.info("List is empty... exiting thread");
  }
}
