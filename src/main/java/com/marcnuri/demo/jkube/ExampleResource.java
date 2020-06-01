package com.marcnuri.demo.jkube;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleResource {

  @GetMapping
  public String hello() {
    return "Hello!!!";
  }
}
