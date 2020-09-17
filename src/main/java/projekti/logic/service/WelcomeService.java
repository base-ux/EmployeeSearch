package projekti.logic.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    
    public String termsofservice() {
        return "termsofservice";
    }
    
    public String welcome() {
        return "welcome";
    }
}
