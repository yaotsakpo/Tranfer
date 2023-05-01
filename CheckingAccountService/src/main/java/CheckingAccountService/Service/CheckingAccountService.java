package CheckingAccountService.Service;

import CheckingAccountService.Domain.CheckingAccount;
import CheckingAccountService.Repository.CheckingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckingAccountService {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;


    public CheckingAccount addSavingAccount(CheckingAccount checkingAccount) {
        return checkingAccountRepository.save(checkingAccount);
    }

    public CheckingAccount deposit(int accountNumber, double amount) {
        Optional<CheckingAccount> savedCheckingAccount = checkingAccountRepository.findById(accountNumber);
        if (savedCheckingAccount.isPresent()) {
            CheckingAccount checkingAccount = savedCheckingAccount.get();
            double newBalance = checkingAccount.getBalance() + amount;
            if (newBalance != 0) {
                checkingAccount.setBalance(newBalance);
            }
            return checkingAccountRepository.save(checkingAccount);
        } else {
            return null;
        }
    }

    public CheckingAccount withdraw(int accountNumber, double amount) {
        Optional<CheckingAccount> savedCheckingAccount = checkingAccountRepository.findById(accountNumber);
        if (savedCheckingAccount.isPresent()) {
            CheckingAccount checkingAccount = savedCheckingAccount.get();
            double newBalance = checkingAccount.getBalance() - amount;
            if (newBalance != 0) {
                checkingAccount.setBalance(newBalance);
            }
            return checkingAccountRepository.save(checkingAccount);
        } else {
            return null;
        }
    }
}
