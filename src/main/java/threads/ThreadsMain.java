package threads;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadsMain {

  public static void main(String[] args) throws InterruptedException {
    final List<Integer> ints = Stream.generate(() -> new Random().nextInt())
        .limit(10000)
        .collect(Collectors.toList());

    final Thread threadA = new Thread(new MagicalListThread(ints));
    final Thread threadB = new Thread(new EmptyingListThread(ints));

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();
  }
}
