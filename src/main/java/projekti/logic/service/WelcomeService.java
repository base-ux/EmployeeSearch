package projekti.logic.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    
    public String termsOfService() {
        return "termsofservice";
    }
    
    public String welcome() {
        return "welcome";
    }
}
