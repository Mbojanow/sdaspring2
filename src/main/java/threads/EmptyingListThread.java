package threads;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyingListThread implements Runnable {

  private List<Integer> ints;

  public EmptyingListThread(final List<Integer> ints) {
    this.ints = ints;
  }

  private synchronized void clearList() {
    while(!ints.isEmpty()) {
      ints.remove(0);
    }
  }

  @Override
  public void run() {
    log.info("Starting to empty list");
    clearList();
    log.info("List is cleared");
  }
}
