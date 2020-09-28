package projekti.logic.control;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import projekti.domain.Account;
import projekti.logic.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/EmployeeSearch/Home")
    public String home() {
        return this.accountService.home();
    }

    @GetMapping("/EmployeeSearch/Login")
    public String login(Model model) {
        return this.accountService.login(model);
    }

    @GetMapping("/EmployeeSearch/Register")
    public String register(Model model, @ModelAttribute Account account) {
        return this.accountService.register(model, account);
    }

    @GetMapping("/EmployeeSearch/Register/{alias}")
    public String registerOk(Model model, @PathVariable String alias) {
        return this.accountService.registerOk(model, alias);
    }
    
    @GetMapping("/login")
    public String rootLogin() {
        return "redirect:/EmployeeSearch/Login";
    }

    @PostMapping("/EmployeeSearch/Register")
    public String addAccount(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        return this.accountService.addAccount(model, account, bindingResult);
    }
}
