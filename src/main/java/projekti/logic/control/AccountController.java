package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String login() {
        return this.accountService.login();
    }

    @GetMapping("/EmployeeSearch/Register")
    public String register(Model model) {
        return this.accountService.register(model);
    }
    
    @PostMapping("/EmployeeSearch/Register")
    public String addaccount(@RequestParam String username, @RequestParam String password) {
        return this.accountService.addaccount(username, password);
    }
}
