package projekti.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;
import projekti.logic.repository.ProfilePictureRepository;

@Service
public class PreferencesService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    public void newProfilePictureStock(String useralias, String profilePictureStock) {
        Account account = this.accountRepository.findByUseralias(useralias);
        account.setStockProfilePicture(profilePictureStock);
        this.accountRepository.save(account);
    }
}
