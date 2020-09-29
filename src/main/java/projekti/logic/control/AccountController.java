package projekti.logic.control;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Account;
import projekti.logic.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/EmployeeSearch/Login")
    public String loginFill(Model model) {
        return this.accountService.loginFill(model);
    }

    @GetMapping("/EmployeeSearch/Register")
    public String registerFill(Model model, @ModelAttribute Account account) {
        return this.accountService.registerFill(model, account);
    }

    @GetMapping("/EmployeeSearch/Register/{alias}")
    public String registerOk(Model model, @PathVariable String alias) {
        return this.accountService.registerOk(model, alias);
    }

    @GetMapping("/EmployeeSearch/Users/{alias}")
    public String userHome(Model model, @PathVariable String alias) {
        return this.accountService.userHome(model, alias);
    }

    @PostMapping("/EmployeeSearch/Login")
    public String loginCheck(Model model, @RequestParam String username,
            @RequestParam String password) {
        return this.accountService.loginCheck(model, username, password);
    }

    @PostMapping("/EmployeeSearch/Register")
    public String registerCheck(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        return this.accountService.registerCheck(model, account, bindingResult);
    }
}
