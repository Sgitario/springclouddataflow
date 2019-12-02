package com.api.sample.restful.sink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(Processor.class)
@Slf4j
public class SinkApplication {

	@StreamListener(Processor.OUTPUT)
	public void handleMessage(String msg) throws InterruptedException {
		log.info("Sink {}", msg);
	}

	public static void main(String[] args) {
		SpringApplication.run(SinkApplication.class, args);
	}
}
