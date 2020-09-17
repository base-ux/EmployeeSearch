package projekti.logic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
