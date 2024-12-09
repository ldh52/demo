package com.lion.demo.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/misc")
public class MiscController {

    @Autowired
    private ApiService apiService;
    @Autowired
    private MetricsService metricsService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/api")
    public String api() {
        String result = apiService.fetchData();
        return result;
    }

    // 오류가 난 부분 : serverPort가 없음
    @GetMapping("/port")
    public String port() {
        return "Server port = " + serverPort;
    }

    @GetMapping("/record-metrics")
    public String recordMetrics() {
        metricsService.recordCustomMetrics();
        return "Custom metrics recorded!";
    }
}