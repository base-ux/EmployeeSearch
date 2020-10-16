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
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;
import projekti.logic.service.AccountService;
import projekti.logic.utility.Date;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    Date date;

    // GET-REQUESTS
    @GetMapping("/EmployeeSearch/LoginError")
    public String loginError(Model model) {
        model.addAttribute("date", this.date.date());
        return "login_error";
    }

    @GetMapping("/EmployeeSearch/Login")
    public String loginFill(Model model) {
        model.addAttribute("date", this.date.date());
        return "login";
    }

    @GetMapping("/EmployeeSearch/Register")
    public String registerFill(Model model, @ModelAttribute Account account) {
        model.addAttribute("accounts", this.accountRepository.findAll());
        model.addAttribute("date", this.date.date());
        return "register";
    }

    @GetMapping("/EmployeeSearch/Register/{alias}")
    public String registerOk(Model model, @PathVariable String alias) {
        model.addAttribute("alias", alias);
        model.addAttribute("date", this.date.date());
        return "register_ok";
    }

    @GetMapping("/EmployeeSearch/TermsOfService")
    public String termsOfService(Model model) {
        this.accountService.helloUser(model);
        model.addAttribute("date", this.date.date());
        return "terms_of_service";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/TermsOfService")
    public String termsOfService(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "terms_of_service";
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}")
    public String userHome(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "home";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Comments")
    public String userComments(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "comments";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Connections")
    public String userConnections(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "connections";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/DeleteProfile")
    public String userDeleteProfile(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "delete_profile";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Help")
    public String userHelp(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "help";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Posts")
    public String userPosts(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "posts";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Preferences")
    public String userPreferences(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "preferences";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Search")
    public String userSearch(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "search";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Uploads")
    public String userUploads(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "uploads";
    }

    @GetMapping("/EmployeeSearch/Welcome")
    public String welcome(Model model) {
        this.accountService.helloUser(model);
        model.addAttribute("date", this.date.date());
        return "welcome";
    }

    @GetMapping("/EmployeeSearch/Users/{alias}/Welcome")
    public String welcome(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "welcome";
    }

    // POST-REQUESTS
    @PostMapping("/EmployeeSearch/Register")
    public String registerCheck(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        return this.accountService.registerCheck(model, account, bindingResult);
    }

    @PostMapping("/EmployeeSearch/Users/{alias}/Search")
    public String userSearchUsers(Model model, @PathVariable String alias) {
        this.accountService.helloUser(model);
        model.addAttribute("account", this.accountRepository.findByAlias(alias));
        model.addAttribute("date", this.date.date());
        return "search";
    }
}
