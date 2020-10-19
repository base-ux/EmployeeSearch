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
    @GetMapping("/EmployeeSearch/Register/{useralias}")
    public String registerOk(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
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
    @GetMapping("/EmployeeSearch/Users/{useralias}/TermsOfService")
    public String termsOfService(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "terms_of_service";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Comments")
    public String userComments(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "comments";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Connections")
    public String userConnections(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "connections";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/DeleteProfile")
    public String userDeleteProfile(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "delete_profile";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Help")
    public String userHelp(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "help";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}")
    public String userHome(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "home";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Posts")
    public String userPosts(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "posts";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Preferences")
    public String userPreferences(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "preferences";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Search")
    public String userSearch(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "search";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Uploads")
    public String userUploads(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "uploads";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Visiting/{visitingalias}")
    public String userVisiting(Model model, @PathVariable String useralias,
            @PathVariable String visitingalias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "visiting_page";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Welcome")
    public String userWelcome(Model model, @PathVariable String useralias) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            return "welcome";
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

    // POST-REQUESTS
    @PostMapping("/EmployeeSearch/Register")
    public String registerCheck(Model model,
            @Valid @ModelAttribute Account account,
            BindingResult bindingResult) {
        return this.accountService.registerCheck(model, account, bindingResult);
    }

    @Secured("USER")
    @PostMapping("/EmployeeSearch/Users/{useralias}")
    public String userHomeEdit(Model model, @PathVariable String useralias,
            @RequestParam String editLayoutButton) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            model.addAttribute("editlayoutClicked", editLayoutButton);
            return "home";
        }
    }

    @Secured("USER")
    @PostMapping("/EmployeeSearch/Users/{useralias}/Search")
    public String userSearchUsers(Model model, @PathVariable String useralias,
            @RequestParam String keyword) {
        boolean isLoggedInUser = this.accountService.helloUser(model, useralias);
        if (keyword.length() == 0) {
            return "redirect:/EmployeeSearch/Users/{useralias}/Search";
        }
        if (isLoggedInUser == false) {
            return "address_error";
        } else {
            model.addAttribute("searchResultsRealname", this.accountService.searchByRealname(keyword));
            model.addAttribute("searchResultsUseralias", this.accountService.searchByUseralias(keyword));
            return "search";
        }
    }
}
