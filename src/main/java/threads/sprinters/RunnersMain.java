package threads.sprinters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunnersMain {

  public static void main(String[] args) throws InterruptedException {
    final List<Long> positions = new ArrayList<>();
    final CountDownLatch countDownLatch = new CountDownLatch(4);

    final List<RunnerThread> threads = Stream
        .generate(() -> new RunnerThread(countDownLatch, positions))
        .limit(4)
        .collect(Collectors.toList());

    final ExecutorService executorService = Executors.newFixedThreadPool(4);
    threads.forEach(executorService::submit);
    //Thread.sleep(5000L);
    executorService.shutdown();
  }
}
