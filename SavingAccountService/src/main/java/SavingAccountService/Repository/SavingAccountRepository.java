package SavingAccountService.Repository;

import SavingAccountService.Domain.SavingAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SavingAccountRepository extends MongoRepository<SavingAccount,Integer> {
}
