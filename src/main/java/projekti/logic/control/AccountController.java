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
    @GetMapping("/EmployeeSearch/Login")
    public String loginFill(Model model) {
        boolean isLoggedInUser = this.accountService.helloUser(model, "notLoggedIn");
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "login";
        }
    }

    @GetMapping("/EmployeeSearch/LoginError")
    public String loginError(Model model) {
        boolean isLoggedInUser = this.accountService.helloUser(model, "notLoggedIn");
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "login_error";
        }
    }

    @GetMapping("/EmployeeSearch/Register")
    public String registerFill(Model model, @ModelAttribute Account account) {
        boolean isLoggedInUser = this.accountService.helloUser(model, "notLoggedIn");
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "register";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Register/{alias}")
    public String registerOk(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "register_ok";
        }
    }

    @GetMapping("/EmployeeSearch/TermsOfService")
    public String termsOfService(Model model) {
        boolean isLoggedInUser = this.accountService.helloUser(model, "notLoggedIn");
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "terms_of_service";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/TermsOfService")
    public String termsOfService(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "terms_of_service";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Comments")
    public String userComments(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "comments";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Connections")
    public String userConnections(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "connections";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/DeleteProfile")
    public String userDeleteProfile(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "delete_profile";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Help")
    public String userHelp(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "help";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}")
    public String userHome(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "home";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Posts")
    public String userPosts(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "posts";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Preferences")
    public String userPreferences(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "preferences";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Search")
    public String userSearch(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "search";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Uploads")
    public String userUploads(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "uploads";
        }
    }

    @GetMapping("/EmployeeSearch/Welcome")
    public String welcome(Model model) {
        boolean isLoggedInUser = this.accountService.helloUser(model, "notLoggedIn");
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "welcome";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{alias}/Welcome")
    public String welcome(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "welcome";
        }
    }

    // POST-REQUESTS
    @PostMapping("/EmployeeSearch/Register")
    public String registerCheck(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        return this.accountService.registerCheck(model, account, bindingResult);
    }

    @Secured("USER")
    @PostMapping("/EmployeeSearch/Users/{alias}")
    public String userHomeEdit(Model model, @PathVariable String alias,
            @RequestParam String editLayoutButton) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            model.addAttribute("editlayoutIsPressed", editLayoutButton);
            return "home";
        }
    }

    @Secured("USER")
    @PostMapping("/EmployeeSearch/Users/{alias}/Search")
    public String userSearchUsers(Model model, @PathVariable String alias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, alias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "search";
        }
    }
}
