package CheckingAccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CheckingAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckingAccountServiceApplication.class, args);
	}

	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}
}
