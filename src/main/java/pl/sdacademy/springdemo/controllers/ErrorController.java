package pl.sdacademy.springdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/error")
public class ErrorController {

  @GetMapping
  public String showErrorPage() {
    return "error";
  }
}
