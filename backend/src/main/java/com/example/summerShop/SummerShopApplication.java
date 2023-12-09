package com.example.summerShop;

import com.example.summerShop.util.runner.RunnerApplicationPrestart;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SummerShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummerShopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RunnerApplicationPrestart prestart) {
		return args -> {
			// prestart.run();
		};
	}

}
