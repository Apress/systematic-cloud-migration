package com.bmi.demo.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Copyright Blue Meridian International
 * @author tgleb
 */

@RestController
public class MetricController {
    Logger logger = LoggerFactory.getLogger(MetricController.class);
    // Metric Counter to collect the amount of Echo calls
    private Counter reqEchoCounter;

    // Metric Counter to collect the amount of Timestamp calls
    private Counter reqTimestampCounter;

    public MetricController(final MeterRegistry registry) {

        // Register the Countere with a metric named and different tags
        reqEchoCounter = registry.counter("data_rest", "usecase", "echo");
        reqTimestampCounter = registry.counter("data_rest", "usecase", "timestamp");
    }

    @GetMapping("/echo/{val}")
    public String simpleEcho(@PathVariable(value = "val") String val) {

        reqEchoCounter.increment();
        logger.info("MetricController.simpleEcho() called:", String.format("Data: {%s}", val));
        return String.format("Data: {%s}", val);
    }

    @GetMapping("/timestamp/{val}")
    public String simpleEchoWithTimestamp(@PathVariable(value = "val") String val) {

        reqTimestampCounter.increment();
        logger.info("MetricController.simpleEchoWithTimestamp() called:}", String.format("Data: %d - {%s}", System.currentTimeMillis(), val));
        return String.format("Data: %d - {%s}", System.currentTimeMillis(), val);
    }
}
