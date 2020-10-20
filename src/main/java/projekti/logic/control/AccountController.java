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

    // LOGGED OUT
    // GET-REQUESTS
    @GetMapping("/EmployeeSearch/Login")
    public String loginFill(Model model) {
        if (this.accountService.helloUser(model, "notLoggedIn") == false) {
            return "fragments/layout_address_error";
        } else {
            return "login";
        }
    }

    @GetMapping("/EmployeeSearch/LoginError")
    public String loginError(Model model) {
        if (this.accountService.helloUser(model, "notLoggedIn") == false) {
            return "fragments/layout_address_error";
        } else {
            return "login_error";
        }
    }

    @GetMapping("/EmployeeSearch/Register")
    public String registerFill(Model model, @ModelAttribute Account account) {
        if (this.accountService.helloUser(model, "notLoggedIn") == false) {
            return "fragments/layout_address_error";
        } else {
            model.addAttribute("showusers", this.accountRepository.findAll());
            return "register";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Register/{useralias}")
    public String registerOk(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "register_ok";
        }
    }

    @GetMapping("/EmployeeSearch/TermsOfService")
    public String termsOfService(Model model) {
        if (this.accountService.helloUser(model, "notLoggedIn") == false) {
            return "fragments/layout_address_error";
        } else {
            return "terms_of_service";
        }
    }

    @GetMapping("/EmployeeSearch/Welcome")
    public String welcome(Model model) {
        if (this.accountService.helloUser(model, "notLoggedIn") == false) {
            return "fragments/layout_address_error";
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

    // LOGGED IN
    // GET-REQUESTS
    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Comments")
    public String userComments(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "comments";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Connections")
    public String userConnections(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "connections";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/DeleteProfile")
    public String userDeleteProfile(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "delete_profile";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Help")
    public String userHelp(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "help";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}")
    public String userHome(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "home";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Edit")
    public String userHomeEdit(Model model, @PathVariable String useralias,
            @RequestParam String editLayoutButton) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            model.addAttribute("editlayoutClicked", editLayoutButton);
            return "home";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Posts")
    public String userPosts(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "posts";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Preferences")
    public String userPreferences(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "preferences";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Search")
    public String userSearch(Model model, @PathVariable String useralias,
            @RequestParam String keyword) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (keyword.length() == 0) {
                return "search";
            }
            model.addAttribute("searchResultsRealname", this.accountService.searchByRealname(keyword));
            model.addAttribute("searchResultsUseralias", this.accountService.searchByUseralias(keyword));
            return "search";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/SearchDropdown")
    public String userSearchDropdown(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "search";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/TermsOfService")
    public String userTermsOfService(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "terms_of_service";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Uploads")
    public String userUploads(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "uploads";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Visiting/{visitingalias}")
    public String userVisiting(Model model, @PathVariable String useralias,
            @PathVariable String visitingalias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (useralias.equals(visitingalias)) {
                return "redirect:/EmployeeSearch/Users/" + useralias;
            }
            model.addAttribute("visitingaccount", this.accountRepository.findByUseralias(visitingalias));
            return "homevisiting";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Visiting/{visitingalias}/AddConnection")
    public String userVisitingAddConnection(Model model, @PathVariable String useralias,
            @PathVariable String visitingalias, @RequestParam String addConnectionButton) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (useralias.equals(visitingalias)) {
                return "redirect:/EmployeeSearch/Users/" + useralias;
            }
            model.addAttribute("connection", addConnectionButton);
            model.addAttribute("visitingaccount", this.accountRepository.findByUseralias(visitingalias));
            return "homevisiting";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Welcome")
    public String userWelcome(Model model, @PathVariable String useralias) {
        if (this.accountService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "welcome";
        }
    }
}
