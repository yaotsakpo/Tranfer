package CheckingAccountService.Repository;

import CheckingAccountService.Domain.CheckingAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends MongoRepository<CheckingAccount,Integer> {
}
