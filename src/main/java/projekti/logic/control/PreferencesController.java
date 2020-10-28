package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import projekti.domain.Ability;
import projekti.logic.service.HomeService;

@Controller
public class PreferencesController {

    @Autowired
    private HomeService homeService;

    // LOGGED IN
    // GET-REQUESTS
    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Preferences")
    public String userPreferences(Model model, @ModelAttribute Ability ability,
            @PathVariable String useralias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            return "preferences";
        }
    }
}
