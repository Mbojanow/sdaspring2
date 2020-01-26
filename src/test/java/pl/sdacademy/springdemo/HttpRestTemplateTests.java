package pl.sdacademy.springdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class HttpRestTemplateTests {

  private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    restTemplate = new RestTemplateBuilder()
        .basicAuthentication("admin", "secret")
        .build();
  }

  @Test
  void shouldGetCourse() {
    final String url = "http://localhost:8080/api/courses/1";
    final Course course = restTemplate.getForObject(url, Course.class);

    System.out.println(course);
  }
}
