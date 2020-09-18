package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.logic.service.PostsService;
import projekti.logic.service.WelcomeService;

@Controller
public class WelcomeController {
    
    @Autowired
    private PostsService postsService;
    
    @Autowired
    private WelcomeService welcomeService;
    
    @GetMapping("/EmployeeSearch/Posts")
    public String posts() {
        return this.postsService.posts();
    }
    
    @GetMapping("/EmployeeSearch/TermsOfService")
    public String termsofservice() {
        return this.welcomeService.termsofservice();
    }
    
    @GetMapping("/EmployeeSearch/Welcome")
    public String welcome() {
        return this.welcomeService.welcome();
    }
}
