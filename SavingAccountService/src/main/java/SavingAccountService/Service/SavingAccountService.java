package SavingAccountService.Service;

import SavingAccountService.Domain.SavingAccount;
import SavingAccountService.Repository.SavingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavingAccountService {

    @Autowired
    SavingAccountRepository savingAccountRepository;

    public SavingAccount addSavingAccount(SavingAccount savingAccount) {
        return savingAccountRepository.save(savingAccount);
    }

    public SavingAccount deposit(int savingAccountNumber, double amount) {
        Optional<SavingAccount> SavedSavingAccount = savingAccountRepository.findById(savingAccountNumber);
        if(SavedSavingAccount.isPresent()) {
            SavingAccount saving = SavedSavingAccount.get();
            double newBalance = saving.getBalance() + amount;
            if(newBalance != 0){
                saving.setBalance(newBalance);
            }
            return savingAccountRepository.save(saving);
        }else {
            return null;
        }
    }

    public SavingAccount withdraw(int savingAccountNumber, double amount) {
        Optional<SavingAccount> SavedSavingAccount = savingAccountRepository.findById(savingAccountNumber);
        if(SavedSavingAccount.isPresent()) {
            SavingAccount saving = SavedSavingAccount.get();
            double newBalance = saving.getBalance() - amount;
            if(newBalance != 0){
                saving.setBalance(newBalance);
            }
            return savingAccountRepository.save(saving);
        }else {
            return null;
        }
    }
}
