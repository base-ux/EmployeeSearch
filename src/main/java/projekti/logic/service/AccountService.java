package projekti.logic.service;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import projekti.domain.Account;
import projekti.logic.repo.AccountRepository;
import projekti.logic.utility.Date;
import projekti.security.EmailValidator;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Date date;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Target({TYPE, FIELD, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Constraint(validatedBy = EmailValidator.class)
    @Documented
    public @interface ValidEmail {

        String message() default "Invalid email";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
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

    public String loginError(Model model) {
        model.addAttribute("date", this.date.date());
        return "login_error";
    }

    public String loginFill(Model model) {
        model.addAttribute("date", this.date.date());
        return "login";
    }

    public String registerCheck(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("date", this.date.date());
            return "register";
        }
        if (this.accountRepository.findByUsername(account.getUsername())
                != null
                || account.getUsername().equals("anonymousUser")
                || account.getUsername().equals("null")) {
            model.addAttribute("date", this.date.date());
            model.addAttribute("username", account.getUsername());
            model.addAttribute("usernameFail", "");
            return "register_error";
        }
        if (this.accountRepository.findByAlias(account.getAlias()) != null) {
            model.addAttribute("alias", account.getAlias());
            model.addAttribute("aliasFail", "");
            model.addAttribute("date", this.date.date());
            model.addAttribute("username", account.getUsername());
            return "register_error";
        }
        if (this.accountRepository.findByEmail(account.getEmail()) != null) {
            model.addAttribute("date", this.date.date());
            model.addAttribute("email", account.getEmail());
            model.addAttribute("emailFail", "");
            return "register_error";
        } else {
            String username = convertRegisterEntry(convertRemoveSpaces(account.getUsername()));
            String password = convertRegisterEntry(account.getPassword());
            String realname = convertRegisterEntry(convertRemoveSpaces(account.getRealname()));
            String alias = convertRegisterEntry(convertRemoveSpaces(account.getAlias()));
            String email = account.getEmail();
            if (username.equals("ERROR") || password.equals("ERROR")
                    || realname.equals("ERROR") || alias.equals("ERROR")) {
                model.addAttribute("date", this.date.date());
                model.addAttribute("entryFail", "");
                return "register_error";
            }
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));
            account.setRealname(realname);
            account.setAlias(alias);
            account.setEmail(email);
        }
        this.accountRepository.save(account);
        return "redirect:/EmployeeSearch/Register/" + account.getAlias();
    }

    public String registerFill(Model model, @ModelAttribute Account account) {
        model.addAttribute("accounts", this.accountRepository.findAll());
        model.addAttribute("date", this.date.date());
        return "register";
    }

    public String registerOk(Model model, String alias) {
        model.addAttribute("alias", alias);
        model.addAttribute("date", this.date.date());
        return "register_ok";
    }

    public String userHome(Model model) {
        model.addAttribute("date", this.date.date());
        return "home";
    }
}
