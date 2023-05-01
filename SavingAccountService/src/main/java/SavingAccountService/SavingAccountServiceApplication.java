package SavingAccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SavingAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavingAccountServiceApplication.class, args);
	}

	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}
}
