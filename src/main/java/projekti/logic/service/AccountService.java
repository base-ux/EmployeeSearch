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
import projekti.logic.utility.Date;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Date date;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String addAccount(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("date", this.date.date());
            return "register";
        }
        if (this.accountRepository.findByUsername(account.getUsername())
                != null) {
            model.addAttribute("date", this.date.date());
            model.addAttribute("username", account.getUsername());
            model.addAttribute("usernameFail", "");
            return "register_fail";
        }
        if (this.accountRepository.findByAlias(account.getAlias()) != null) {
            model.addAttribute("alias", account.getAlias());
            model.addAttribute("aliasFail", "");
            model.addAttribute("date", this.date.date());
            model.addAttribute("username", account.getUsername());
            return "register_fail";
        } else {
            String username = convertRegisterEntry(convertRemoveSpaces(account.getUsername()));
            String password = convertRegisterEntry(account.getPassword());
            String realname = convertRegisterEntry(convertRemoveSpaces(account.getRealname()));
            String alias = convertRegisterEntry(convertRemoveSpaces(account.getAlias()));
            if (username.equals("ERROR") || password.equals("ERROR")
                    || realname.equals("ERROR") || alias.equals("ERROR")) {
                model.addAttribute("date", this.date.date());
                model.addAttribute("entryFail", "");
                return "register_fail";
            }
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));
            account.setRealname(realname);
            account.setAlias(alias);
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
        return converted;
    }

    public String convertRemoveSpaces(String convert) {
        String converted = convert;
        converted = converted.trim().replaceAll("\\s+", "");
        return converted;
    }

    public String home() {
        return "home";
    }

    public String login(Model model) {
        model.addAttribute("date", this.date.date());
        return "login";
    }

    public String register(Model model, @ModelAttribute Account account) {
        model.addAttribute("accounts", this.accountRepository.findAll());
        model.addAttribute("date", this.date.date());
        return "register";
    }

    public String registerOk(Model model, @PathVariable String alias) {
        model.addAttribute("alias", alias);
        model.addAttribute("date", this.date.date());
        return "register_ok";
    }
}
