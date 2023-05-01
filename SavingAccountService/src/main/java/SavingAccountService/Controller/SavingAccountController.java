package SavingAccountService.Controller;


import SavingAccountService.Domain.CheckingAccount;
import SavingAccountService.Domain.SavingAccount;
import SavingAccountService.Service.SavingAccountService;
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
@RequestMapping("/savingAccount")
public class SavingAccountController {

    @Autowired
    private RestOperations restTemplate;

    private String serverUrl = "http://localhost:8081/";

    @Autowired
    SavingAccountService savingAccountService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SavingAccount savingAccount){
        SavingAccount responseSavingAccount = savingAccountService.addSavingAccount(savingAccount);
        if(responseSavingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseSavingAccount, HttpStatus.OK);
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
        SavingAccount responseSavingAccount = savingAccountService.deposit(request.getAccountNumber(),request.getAmount());
        if(responseSavingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseSavingAccount, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody RequestUpdateWrapper request){
        SavingAccount responseSavingAccount = savingAccountService.withdraw(request.getAccountNumber(),request.getAmount());
        if(responseSavingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseSavingAccount, HttpStatus.OK);
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

        SavingAccount responseSavingAccount = savingAccountService.withdraw(savingAccount,amount);
        if(responseSavingAccount == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        restTemplate.postForLocation(serverUrl+"/checkingAccount/deposit", new RequestUpdateWrapper(checkingAccount,amount));

        return new ResponseEntity<>(responseSavingAccount, HttpStatus.OK);
    }
}

