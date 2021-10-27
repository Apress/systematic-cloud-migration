package com.bmi.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Copyright Blue Meridian International
 * @author tgleb
 */

@SpringBootApplication
public class SpringBootDemoApplication {

	static Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
		logger.debug("SpringBootDemoApplication.main() is starting....");
	}

}
