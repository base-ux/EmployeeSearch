package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Account;
import projekti.domain.Praise;
import projekti.logic.repository.AccountRepository;
import projekti.logic.repository.PraiseRepository;
import projekti.logic.service.AbilityService;
import projekti.logic.service.ConnectionsService;
import projekti.logic.service.HomeService;

@Controller
public class HomeController {

    @Autowired
    private AbilityService abilityService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ConnectionsService connectionsService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private PraiseRepository praiseRepository;

    // LOGGED IN
    // GET-REQUESTS
    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}")
    public String userHome(Model model, @PathVariable String useralias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            Account account = this.accountRepository.findByUseralias(useralias);
            model.addAttribute("stockProfilePicture", account.getStockProfilePicture());
            model.addAttribute("viewFirstAbilities", this.abilityService.viewFirstAbilities(account));
            model.addAttribute("viewLastAbilities", this.abilityService.viewLastAbilities(account));
            model.addAttribute("viewAllPraises", this.praiseRepository.findByAccount(account));
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
            if (editLayoutButton.equals("finishedEditing")) {
                return "redirect:/EmployeeSearch/Users/" + useralias;
            } else {
                Account account = this.accountRepository.findByUseralias(useralias);
                model.addAttribute("editlayoutClicked", editLayoutButton);
                model.addAttribute("stockProfilePicture", account.getStockProfilePicture());
                return "home";
            }
        }
    }

    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Visiting/{visitingalias}")
    public String userVisiting(Model model, @ModelAttribute Praise praise,
            @PathVariable String useralias, @PathVariable String visitingalias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (useralias.equals(visitingalias)) {
                return "redirect:/EmployeeSearch/Users/" + useralias;
            }
            Account userAccount = this.accountRepository.findByUseralias(useralias);
            Account visitingAccount = this.accountRepository.findByUseralias(visitingalias);
            model.addAttribute("connectionEstablishedVisited", visitingAccount.getConnectionsEstablished());
            model.addAttribute("connectionEstablishedVisitedSize", visitingAccount.getConnectionsEstablished().size());
            model.addAttribute("connectionEstablished", false);
            if (this.connectionsService.connectionIsEstablished(userAccount, visitingAccount) == true) {
                model.addAttribute("connectionEstablished", true);
            }
            model.addAttribute("requestSent", false);
            if (this.connectionsService.requestIsSent(userAccount, visitingAccount) == true) {
                model.addAttribute("requestSent", true);
            }
            model.addAttribute("requestReceived", false);
            if (this.connectionsService.requestIsReceived(userAccount, visitingAccount) == true) {
                model.addAttribute("requestReceived", true);
            }
            model.addAttribute("visitingaccount", visitingAccount);
            model.addAttribute("visitingProfilePicture", visitingAccount.getStockProfilePicture());
            model.addAttribute("viewFirstAbilities", this.abilityService.viewFirstAbilities(visitingAccount));
            model.addAttribute("viewLastAbilities", this.abilityService.viewLastAbilities(visitingAccount));
            model.addAttribute("viewAllPraises", this.praiseRepository.findByAccount(visitingAccount));
            return "homevisiting";
        }
    }

    // LOGGED IN
    // POST-REQUESTS
    @Secured("USER")
    @PostMapping("/EmployeeSearch/Users/{useralias}/Visiting/{visitingalias}/AddConnection")
    public String userVisitingAddConnection(Model model, @PathVariable String useralias,
            @PathVariable String visitingalias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (useralias.equals(visitingalias)) {
                return "redirect:/EmployeeSearch/Users/" + useralias;
            }
            Account userAccount = this.accountRepository.findByUseralias(useralias);
            Account visitingAccount = this.accountRepository.findByUseralias(visitingalias);
            if (this.connectionsService.connectionRequestSent(userAccount, visitingAccount) == true) {
                model.addAttribute("requestSent", true);
            } else {
                this.connectionsService.connectionRequestReceived(userAccount, visitingAccount);
                model.addAttribute("requestSent", false);
            }
            model.addAttribute("visitingaccount", visitingAccount);
            return "redirect:/EmployeeSearch/Users/" + useralias + "/Visiting/" + visitingalias;
        }
    }
}
