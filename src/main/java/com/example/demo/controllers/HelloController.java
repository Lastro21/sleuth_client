package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    private RestTemplate restTemplate;

    @RequestMapping(value = "/hello")
    public String rrt() {

        String traceId = MDC.get("X-B3-TraceId");

        LOGGER.info("BEFORE Calling the Server. traceId: " + traceId);
        String response = restTemplate.getForObject("http://localhost:8090/test_sleuth", String.class);
        LOGGER.info("AFTER Calling the Server. traceId: " + traceId);

        return "Simple string from hello controller (/hello) get Response = " + response;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
