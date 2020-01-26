package pl.sdacademy.springdemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
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

  @Test
  void whatHappensIfCredentialsAreIncorrect() {
    restTemplate = new RestTemplateBuilder()
        .messageConverters(new MappingJackson2HttpMessageConverter())
        .build();

    final String url = "http://localhost:8080/api/courses/1";
    assertThatExceptionOfType(HttpStatusCodeException.class)
        .isThrownBy(() -> restTemplate.getForObject(url, Course.class))
    .withCause(null);
  }

  @Test
  void shouldPassWhenHttpDemoProjectIsNotRunning() {
    restTemplate = new RestTemplateBuilder()
        .messageConverters(new MappingJackson2HttpMessageConverter())
        .build();

    final String url = "http://localhost:8080/api/courses/1";
    assertThatExceptionOfType(RestClientException.class)
        .isThrownBy(() -> restTemplate.getForObject(url, Course.class))
        .withCause(null);
  }

  @Test
  void shouldCreateCourse() {
    final String url = "http://localhost:8080/api/courses";
    final Course course = new Course(null, "Python", "Pajton kurs", 999L);
    final ResponseEntity<Void> response = restTemplate.postForEntity(url, course, Void.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }

  @Test
  void shouldUpdateCourse() {
    final String url = "http://localhost:8080/api/courses/{id}";
    final Course updatedCourse = new Course(1L, "JS", "Node stuff", 499L);
    restTemplate.put(url, updatedCourse, Map.of("id", 1L));
  }

  @Test
  void shouldGetCourseWithCustomHeader() {
    restTemplate = new RestTemplateBuilder()
        .messageConverters(new MappingJackson2HttpMessageConverter())
        .build();
    final Course course = new Course(null, "Python", "Pajton kurs", 999L);

    final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.put("Authorization", List.of("Basic " + Base64.getEncoder().encode(
        "admin:secret".getBytes()
    )));
    HttpEntity<Course> entity = new HttpEntity<>(course, headers);

    restTemplate.postForEntity("http://localhost:8080/api/courses", entity, Void.class);
  }
}
