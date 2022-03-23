package com.emlakburada.purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmlakburadaPurchaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmlakburadaPurchaseApplication.class, args);
	}

}
