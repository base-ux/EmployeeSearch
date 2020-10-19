package projekti.logic.service;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;
import projekti.logic.utility.Date;
import projekti.security.PasswordConstraintValidator;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Date date;

    @Autowired
    HttpServletRequest request;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Documented
    @Constraint(validatedBy = PasswordConstraintValidator.class)
    @Target({TYPE, FIELD, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    public @interface ValidPassword {

        String message() default "Invalid Password";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

    }

    //    Allows only numbers, uppercase letters, lowercase letters, space, &, @, _
    public String convertRegisterEntry(String convert) {
        String converted = "";
        for (int i = 0; i < convert.length(); i++) {
            char c = convert.charAt(i);
            if (((int) c >= 48 && (int) c <= 57)
                    || ((int) c >= 65 && (int) c <= 90)
                    || ((int) c >= 97 && (int) c <= 122)
                    || ((int) c == 32)
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

    public boolean helloUser(Model model, String checkUseralias) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username.equals("anonymousUser") || username.equals("null")) {
            model.addAttribute("hellouseralias", "Welcome, visitor!");
            model.addAttribute("loggedinuser", "");
        } else {
            Principal principal = this.request.getUserPrincipal();
            String principalName = principal.getName();
            Account principalAccount = this.accountRepository.findByUsername(principalName);
            if (!principalAccount.getUseralias().equals(checkUseralias)) {
                model.addAttribute("date", this.date.date());
                return false;
            } else {
                String useralias = principalAccount.getUseralias();
                if (useralias.length() > 9) {
                    model.addAttribute("account", principalAccount);
                    model.addAttribute("date", this.date.date());
                    model.addAttribute("hellouseralias", "Hello, " + useralias.substring(0, 9) + "...!");
                    model.addAttribute("loggedinuser", useralias.substring(0, 9) + "...");
                    return true;
                } else {
                    model.addAttribute("account", principalAccount);
                    model.addAttribute("date", this.date.date());
                    model.addAttribute("hellouseralias", "Hello, " + useralias + " !");
                    model.addAttribute("loggedinuser", useralias);
                    return true;
                }
            }
        }
        return true;
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
        if (!account.getPassword().equals(account.getConfirm())) {
            model.addAttribute("confirmFail", "");
            model.addAttribute("date", this.date.date());
            model.addAttribute("username", account.getUsername());
            return "register_error";
        }
        if (this.accountRepository.findByUseralias(account.getUseralias()) != null) {
            model.addAttribute("useralias", account.getUseralias());
            model.addAttribute("useraliasFail", "");
            model.addAttribute("date", this.date.date());
            model.addAttribute("username", account.getUsername());
            return "register_error";
        } else {
            String username = convertRegisterEntry(convertRemoveSpaces(account.getUsername()));
            String password = convertRegisterEntry(account.getPassword());
            String confirm = convertRegisterEntry(account.getConfirm());
            String realname = convertRegisterEntry(account.getRealname());
            String useralias = convertRegisterEntry(convertRemoveSpaces(account.getUseralias()));
            if (username.equals("ERROR") || password.equals("ERROR")
                    || realname.equals("ERROR") || useralias.equals("ERROR")) {
                model.addAttribute("date", this.date.date());
                model.addAttribute("entryFail", "");
                return "register_error";
            }
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));
            account.setConfirm(confirm);
            account.setRealname(realname);
            account.setUseralias(useralias);
        }
        this.accountRepository.save(account);
        return "redirect:/EmployeeSearch/Register/" + account.getUseralias();
    }

    public List<Account> searchByRealname(String keyword) {
        List<Account> results = this.accountRepository.findByRealnameContainingIgnoreCase(keyword);
        if (results.size() > 0) {
            return results;
        } else {
            results.add(new Account());
            return results;
        }
    }

    public List<String> searchByUseralias(String keyword) {
        List<String> results = new ArrayList<>();
        List<Account> accountsByUseralias = this.accountRepository.findByUseraliasContainingIgnoreCase(keyword);
        if (accountsByUseralias.size() > 0) {
            accountsByUseralias.forEach(a -> results.add(a.getUseralias()));
            return results;
        } else {
            results.add("empty");
            return results;
        }
    }
}
