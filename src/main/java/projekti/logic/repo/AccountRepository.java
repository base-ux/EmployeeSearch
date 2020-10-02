package projekti.logic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAlias(String alias);

    Account findByEmail(String email);

    Account findByUsername(String username);
}
