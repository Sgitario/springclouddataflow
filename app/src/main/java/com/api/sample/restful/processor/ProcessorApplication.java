package com.api.sample.restful.processor;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;

import com.api.sample.restful.source.SourceApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(Source.class)
@Slf4j
public class ProcessorApplication {

	private static final long MILLIS = 1_000l;

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String handleMessage(String msg) throws InterruptedException {
		log.info("Consumer {}", msg);
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Thread.sleep(random.nextLong(25, 30) * MILLIS);
		log.info("Done! {}", msg);
		return msg;
	}

	public static void main(String[] args) {
		SpringApplication.run(SourceApplication.class, args);
	}
}
