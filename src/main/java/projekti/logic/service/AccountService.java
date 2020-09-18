package projekti.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import projekti.domain.Account;
import projekti.logic.repo.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String addaccount(String username, String password) {
        if (this.accountRepository.findByUsername(username) != null) {
            return "redirect:/EmployeeSearch/Register";
        }

        Account account = new Account(username, passwordEncoder.encode(password));
        this.accountRepository.save(account);
        return "redirect:/EmployeeSearch/Register";
    }

    public String home() {
        return "home";
    }
    
    public String login() {
        return "home";
    }

    public String register(Model model) {
        model.addAttribute("accounts", this.accountRepository.findAll());
        return "register";
    }
}
