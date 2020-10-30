package projekti.logic.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Ability;
import projekti.logic.service.HomeService;
import projekti.logic.service.PreferencesService;

@Controller
public class PreferencesController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private PreferencesService preferencesService;

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

    // LOGGED IN
    // POST-REQUESTS
    @Secured("USER")
    @RequestMapping(value = "/EmployeeSearch/Users/{useralias}/ProfilePictureStock", method = RequestMethod.POST)
    public String profilePictureStockNew(Model model, @PathVariable String useralias,
            @RequestParam(required = false) String profilePictureStock) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            this.preferencesService.newProfilePictureStock(useralias, profilePictureStock);
            return "redirect:/EmployeeSearch/Users/" + useralias;
        }
    }
}
