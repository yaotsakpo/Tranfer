package CheckingAccountService.Controller;

import CheckingAccountService.Domain.CheckingAccount;
import CheckingAccountService.Domain.SavingAccount;
import CheckingAccountService.Service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/checkingAccount")
public class CheckingAccountController {

    @Autowired
    private RestOperations restTemplate;

    String serverUrl = "http://localhost:8082/";

    @Autowired
    CheckingAccountService checkingAccountService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CheckingAccount checkingAccount){
        CheckingAccount responseCheckingAccount = checkingAccountService.addSavingAccount(checkingAccount);
        if(responseCheckingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseCheckingAccount, HttpStatus.OK);
    }

    public static class RequestUpdateWrapper {
        private int accountNumber;
        private double amount;

        public RequestUpdateWrapper(int accountNumber, double amount) {
            this.accountNumber = accountNumber;
            this.amount = amount;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public double getAmount() {
            return amount;
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody RequestUpdateWrapper request){
        CheckingAccount responseCheckingAccount = checkingAccountService.deposit(request.getAccountNumber(),request.getAmount());
        if(responseCheckingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseCheckingAccount, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody RequestUpdateWrapper request){
        CheckingAccount responseCheckingAccount = checkingAccountService.withdraw(request.getAccountNumber(),request.getAmount());
        if(responseCheckingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseCheckingAccount, HttpStatus.OK);
    }

    public static class RequestWrapper {
        private int fromAccountNumber;
        private int toAccountNumber;
        private double amount;

        public int getFromAccountNumber() {
            return fromAccountNumber;
        }

        public int getToAccountNumber() {
            return toAccountNumber;
        }

        public double getAmount() {
            return amount;
        }
    }

    @PostMapping("/transfert")
    public ResponseEntity<?> transfert(@RequestBody RequestWrapper request){

        int checkingAccount = request.getFromAccountNumber();
        int savingAccount = request.getToAccountNumber();
        double amount = request.getAmount();

        CheckingAccount responseCheckingAccount = checkingAccountService.withdraw(checkingAccount,amount);
        if(responseCheckingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        restTemplate.postForLocation(serverUrl+"/savingAccount/deposit", new RequestUpdateWrapper(savingAccount,amount));

        return new ResponseEntity<>(responseCheckingAccount, HttpStatus.OK);
    }

}
