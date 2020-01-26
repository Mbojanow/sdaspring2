package pl.sdacademy.springdemo;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class HttpRestTemplateTests {

  private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    restTemplate = new RestTemplateBuilder()
        .basicAuthentication("admin", "secret")
        .messageConverters(new MappingJackson2HttpMessageConverter())
        .build();
  }

  @Test
  void shouldGetCourse() {
    final String url = "http://localhost:8080/api/courses/1";
    final Course course = restTemplate.getForObject(url, Course.class);

    System.out.println(course);
  }

  @Test
  @SuppressWarnings("unchecked")
  void shouldGetAllCourses() {
    final String url = "http://localhost:8080/api/courses";
    final List<Object> courses = restTemplate.getForObject(url, List.class);
    courses.forEach(System.out::println);
  }

  @Test
  void shouldGetEntityOfACourse() {
    final String url = "http://localhost:8080/api/courses/1";
    final ResponseEntity<Course> courseResponseEntity = restTemplate.getForEntity(url, Course.class);

    //LinkedHashMap - x form url encoded
    System.out.println(courseResponseEntity.getStatusCode());
    System.out.println(courseResponseEntity.getBody());
  }
}
