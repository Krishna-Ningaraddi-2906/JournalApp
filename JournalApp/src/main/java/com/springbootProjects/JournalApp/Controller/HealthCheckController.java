package com.springbootProjects.JournalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController
{
    @GetMapping("/Health-Check")
  public String healthCheck()
  {
      return "Ok";
  }
}
