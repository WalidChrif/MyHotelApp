package com.myhotel.booking_management;

import com.myhotel.booking_management.model.BookingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;


@EnableFeignClients
@SpringBootApplication
public class BookingManagementApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookingManagementApplication.class, args);
	}

	@KafkaListener(topics = {"roomTopic", "customerTopic"}, groupId = "bookingId")
	public void listen(String message){
        LOGGER.info("Received message through kafka {}" , message);
	}

	@RabbitListener(queues = {"${rabbitmq.queue.name}"})
	public void receive(Object object){
		LOGGER.info("receiving through rabbitmq --> {}", object.toString());
	}

}
