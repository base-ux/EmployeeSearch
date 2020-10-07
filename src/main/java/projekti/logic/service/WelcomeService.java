package projekti.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;
import projekti.logic.utility.Date;

@Service
public class WelcomeService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Date date;

    public String termsOfService() {
        return "terms_of_service";
    }

    public String welcome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username.equals("anonymousUser") || username.equals("null")) {
            model.addAttribute("helloalias", "Welcome, visitor!");
            model.addAttribute("loggedinuser", "");
        } else {
            Account account = this.accountRepository.findByUsername(username);
            String alias = account.getAlias();
            model.addAttribute("helloalias", "Hello, " + alias + "!");
            model.addAttribute("loggedinuser", alias);
        }
        model.addAttribute("date", this.date.date());
        return "welcome";
    }
}
