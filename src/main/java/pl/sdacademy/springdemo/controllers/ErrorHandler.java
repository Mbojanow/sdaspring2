package pl.sdacademy.springdemo.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandler extends AbstractErrorController {

  public ErrorHandler(final ErrorAttributes errorAttributes) {
    super(errorAttributes);
  }

  @RequestMapping("/error")
  public String handlerError(final HttpServletRequest request, final ModelMap modelMap) {
    final Map<String, Object> errorAttributes = getErrorAttributes(request, false);
    modelMap.addAllAttributes(errorAttributes);
    return "error_page";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
