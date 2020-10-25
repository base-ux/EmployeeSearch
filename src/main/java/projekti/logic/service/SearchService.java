package projekti.logic.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;

@Service
public class SearchService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> removeUserAccountFromResultsOrReturnEmptyList(List<Account> results, String useralias) {
        if (results.size() > 0) {
            results.remove(this.accountRepository.findByUseralias(useralias));
            return results;
        }
        return results;
    }

    public List<Account> searchRealnames(String useralias, String keyword) {
        List<Account> results = this.accountRepository.findByRealnameContainingIgnoreCase(keyword);
        return removeUserAccountFromResultsOrReturnEmptyList(results, useralias);
    }

    public List<Account> searchUseraliases(String useralias, String keyword) {
        List<Account> results = this.accountRepository.findByUseraliasContainingIgnoreCase(keyword);
        return removeUserAccountFromResultsOrReturnEmptyList(results, useralias);
    }
}
