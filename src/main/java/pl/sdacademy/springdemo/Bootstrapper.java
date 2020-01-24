package pl.sdacademy.springdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Bootstrapper implements CommandLineRunner {
    @Transactional
    @Override
    public void run(final String... args) throws Exception {
        throw new RuntimeException();
    }
}
