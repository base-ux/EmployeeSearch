package projekti.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import projekti.logic.utility.Date;

@Service
public class WelcomeService {
    
    @Autowired
    Date date;
    
    public String termsOfService() {
        return "terms_of_service";
    }
    
    public String welcome(Model model) {
        model.addAttribute("date", this.date.date());
        return "welcome";
    }
}
