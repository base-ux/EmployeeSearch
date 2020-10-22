package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;
import projekti.logic.service.ConnectionsService;
import projekti.logic.service.HomeService;

@Controller
public class HomeController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ConnectionsService connectionsService;

    @Autowired
    private HomeService homeService;

    // LOGGED IN
    // GET-REQUESTS
    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}")
    public String userHome(Model model, @PathVariable String useralias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "home";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Edit")
    public String userHomeEdit(Model model, @PathVariable String useralias,
            @RequestParam String editLayoutButton) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            model.addAttribute("editlayoutClicked", editLayoutButton);
            return "home";
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Visiting/{visitingalias}")
    public String userVisiting(Model model, @PathVariable String useralias,
            @PathVariable String visitingalias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (useralias.equals(visitingalias)) {
                return "redirect:/EmployeeSearch/Users/" + useralias;
            }
            Account userAccount = this.accountRepository.findByUseralias(useralias);
            Account visitingAccount = this.accountRepository.findByUseralias(visitingalias);
            model.addAttribute("connection", false);
            for (String[] s : userAccount.getConnectionRequestsSent()) {
                if (s[0].equals(visitingAccount.getUseralias())) {
                    model.addAttribute("connection", true);
                }
            }
            model.addAttribute("visitingaccount", visitingAccount);
            return "homevisiting";
        }
    }

    // LOGGED IN
    // POST-REQUESTS
    @Secured("USER")
    @PostMapping("/EmployeeSearch/Users/{useralias}/Visiting/{visitingalias}/AddConnection")
    public String userVisiting(Model model, @PathVariable String useralias,
            @PathVariable String visitingalias, @RequestParam String addConnectionButton) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (useralias.equals(visitingalias)) {
                return "redirect:/EmployeeSearch/Users/" + useralias;
            }
            Account visitingAccount = this.accountRepository.findByUseralias(visitingalias);
            if (addConnectionButton.equals("addConnectionButtonPressed")) {
                Account userAccount = this.accountRepository.findByUseralias(useralias);
                if (this.connectionsService.connectionRequestSent(userAccount, visitingAccount) == true) {
                    model.addAttribute("connection", true);
                } else {
                    this.connectionsService.connectionRequestReceived(userAccount, visitingAccount);
                    model.addAttribute("connection", false);
                }
            }
            model.addAttribute("visitingaccount", visitingAccount);
            return "redirect:/EmployeeSearch/Users/" + useralias + "/Visiting/" + visitingalias;
        }
    }
}
