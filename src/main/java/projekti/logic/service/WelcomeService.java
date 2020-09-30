package projekti.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username.equals("anonymousUser") || username.equals("null")) {
            model.addAttribute("hellouser", "Welcome, visitor!");
            model.addAttribute("loggedinuser", "");
        } else {
            model.addAttribute("hellouser", "Hello, " + username + "!");
            model.addAttribute("loggedinuser", username);
        }
        model.addAttribute("date", this.date.date());
        return "welcome";
    }
}
