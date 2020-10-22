package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;
import projekti.logic.service.HomeService;

@Controller
public class ConnectionsController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private HomeService homeService;

    // LOGGED IN
    // GET-REQUESTS
    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Connections")
    public String userConnections(Model model, @PathVariable String useralias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            Account userAccount = this.accountRepository.findByUseralias(useralias);
            model.addAttribute("requestsReceived", userAccount.getConnectionRequestsReceived());
            model.addAttribute("requestsSent", userAccount.getConnectionRequestsSent());
            return "connections";
        }
    }
}
