package pl.sdacademy.springdemo.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomErrorController {

  private static final String MODEL_ERROR_DETAILS = "errDetails";

  @ExceptionHandler(Exception.class)
  public String handleException(final Exception exp, final ModelMap modelMap) {
    modelMap.addAttribute(MODEL_ERROR_DETAILS, exp.getMessage());
    return "error";
  }
}
