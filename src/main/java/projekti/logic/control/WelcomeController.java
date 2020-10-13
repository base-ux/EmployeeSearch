package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.logic.service.WelcomeService;

@Controller
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/EmployeeSearch/TermsOfService")
    public String termsOfService() {
        return "terms_of_service";
    }

    @GetMapping("/EmployeeSearch/Welcome")
    public String welcome(Model model) {
        return this.welcomeService.welcome(model);
    }
}
