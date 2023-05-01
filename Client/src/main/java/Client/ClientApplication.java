package Client;

import Client.Domain.CheckingAccount;
import Client.Domain.SavingAccount;
import Client.Utilis.RequestUpdateWrapper;
import Client.Utilis.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

	@Autowired
	private RestOperations restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CheckingAccount checkingAccount = new CheckingAccount(1,2000);
		SavingAccount savingAccount = new SavingAccount(2,3000);

		restTemplate.postForLocation("http://localhost:8081/checkingAccount/add", checkingAccount);
		restTemplate.postForLocation("http://localhost:8081/checkingAccount/deposit", new RequestUpdateWrapper(
				checkingAccount.getAccountNumber(),3000)
		);
		restTemplate.postForLocation("http://localhost:8081/checkingAccount/withdraw", new RequestUpdateWrapper(
				checkingAccount.getAccountNumber(),2000)
		);

		restTemplate.postForLocation("http://localhost:8082/savingAccount/add", savingAccount);
		restTemplate.postForLocation("http://localhost:8082/savingAccount/deposit", new RequestUpdateWrapper(
				savingAccount.getAccountNumber(),3000)
		);
		restTemplate.postForLocation("http://localhost:8082/savingAccount/withdraw", new RequestUpdateWrapper(
				savingAccount.getAccountNumber(),2000)
		);


		restTemplate.postForLocation("http://localhost:8082/savingAccount/transfert", new RequestWrapper(
				checkingAccount.getAccountNumber(),
				savingAccount.getAccountNumber(),
				1000)
		);

		restTemplate.postForLocation("http://localhost:8081/checkingAccount/transfert", new RequestWrapper(
				savingAccount.getAccountNumber(),
				checkingAccount.getAccountNumber(),
				2000)
		);

	}

	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}

}
