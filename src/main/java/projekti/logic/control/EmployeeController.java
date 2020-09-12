package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.logic.service.WelcomeService;

@Controller
public class EmployeeController {
    
    @Autowired
    private WelcomeService welcomeService;
    
    @GetMapping("/EmployeeSearch/Welcome")
    public String welcome() {
        return this.welcomeService.welcome();
    }
}
