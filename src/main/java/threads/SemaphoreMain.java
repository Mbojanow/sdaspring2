package threads;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SemaphoreMain {
  public static void main(String[] args) {

    final List<Integer> ints = Stream.generate(() -> new Random().nextInt())
        .limit(10000)
        .collect(Collectors.toList());
    final Semaphore semaphore = new Semaphore(0);

    final ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(new EmptyingListThreadWithSemaphore(ints, semaphore));
    executorService.submit(new MagicalListThreadWithSemaphore(ints, semaphore));

    executorService.shutdown();
  }
}
