package projekti.logic.service;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import projekti.domain.Account;
import projekti.logic.repo.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String addAccount(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (this.accountRepository.findByUsername(account.getUsername())
                != null) {
            model.addAttribute("username", account.getUsername());
            model.addAttribute("usernameFail", "");
            return "registerfail";
        }
        if (this.accountRepository.findByAlias(account.getAlias()) != null) {
            model.addAttribute("alias", account.getAlias());
            model.addAttribute("aliasFail", "");
            model.addAttribute("username", account.getUsername());
            return "registerfail";
        }
        if (convertRegisterEntry(account.getUsername()).equals("ERROR")
                || convertRegisterEntry(account.getPassword()).equals("ERROR")
                || convertRegisterEntry(account.getRealname()).equals("ERROR")
                || convertRegisterEntry(account.getAlias()).equals("ERROR")) {
            model.addAttribute("entryFail", "");
            return "registerfail";
        } else {
            account.setUsername(convertRegisterEntry(account.getUsername()));
            String password = convertRegisterEntry(account.getPassword());
            account.setPassword(passwordEncoder.encode(password));
            account.setRealname(convertRegisterEntry(account.getRealname()));
            account.setAlias(convertRegisterEntry(account.getAlias()));
        }
        this.accountRepository.save(account);
        return "redirect:/EmployeeSearch/Register/" + account.getAlias();
    }

    public String convertRegisterEntry(String convert) {
        String converted = "";
        for (int i = 0; i < convert.length(); i++) {
            char c = convert.charAt(i);
            if (((int) c >= 48 && (int) c <= 57)
                    || ((int) c >= 65 && (int) c <= 90)
                    || ((int) c >= 97 && (int) c <= 122)
                    || ((int) c == 38)
                    || ((int) c == 64)
                    || ((int) c == 95)) {
                converted += c;
            } else {
                converted = "ERROR";
                return converted;
            }
        }
        converted = converted.trim().toLowerCase().replaceAll("\\s+", "");
        return converted;
    }

    public String home() {
        return "home";
    }

    public String login() {
        return "home";
    }

    public String register(Model model, @ModelAttribute Account account) {
        model.addAttribute("accounts", this.accountRepository.findAll());
        return "register";
    }

    public String registerOk(Model model, @PathVariable String alias) {
        model.addAttribute("alias", alias);
        return "registerok";
    }
}
