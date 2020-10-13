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

//    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users")
    public String userHome(Model model) {
        model.addAttribute("date", this.date.date());
        return "home";
    }

    @GetMapping("/EmployeeSearch/Users/Comments")
    public String userComments(Model model) {
        model.addAttribute("date", this.date.date());
        return "comments";
    }

    @GetMapping("/EmployeeSearch/Users/Connections")
    public String userConnections(Model model) {
        model.addAttribute("date", this.date.date());
        return "connections";
    }

    @GetMapping("/EmployeeSearch/Users/DeleteProfile")
    public String userDeleteProfile(Model model) {
        model.addAttribute("date", this.date.date());
        return "delete_profile";
    }

    @GetMapping("/EmployeeSearch/Users/Help")
    public String userHelp(Model model) {
        model.addAttribute("date", this.date.date());
        return "help";
    }

    @GetMapping("/EmployeeSearch/Users/Posts")
    public String userPosts(Model model) {
        model.addAttribute("date", this.date.date());
        return "posts";
    }

    @GetMapping("/EmployeeSearch/Users/Preferences")
    public String userPreferences(Model model) {
        model.addAttribute("date", this.date.date());
        return "preferences";
    }

    @GetMapping("/EmployeeSearch/Users/Search")
    public String userSearch(Model model) {
        model.addAttribute("date", this.date.date());
        return "search";
    }

    @GetMapping("/EmployeeSearch/Users/Uploads")
    public String userUploads(Model model) {
        model.addAttribute("date", this.date.date());
        return "uploads";
    }

    @PostMapping("/EmployeeSearch/Register")
    public String registerCheck(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        return this.accountService.registerCheck(model, account, bindingResult);
    }

    @PostMapping("/EmployeeSearch/Users/Search")
    public String userSearchUsers(Model model) {
        model.addAttribute("date", this.date.date());
        return "search";
    }
}
