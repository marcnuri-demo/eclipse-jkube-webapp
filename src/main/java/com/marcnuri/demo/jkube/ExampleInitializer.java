package com.marcnuri.demo.jkube;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ExampleInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext servletContext) {
    final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(ExampleConfiguration.class);
    context.setServletContext(servletContext);
    final ServletRegistration.Dynamic dsr = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
    dsr.setLoadOnStartup(1);
    dsr.addMapping("/");
  }
}
