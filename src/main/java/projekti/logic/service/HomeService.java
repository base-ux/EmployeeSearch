package projekti.logic.service;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import projekti.domain.Account;
import projekti.logic.repository.AccountRepository;
import projekti.logic.utility.Date;

@Service
public class HomeService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Date date;

    @Autowired
    HttpServletRequest request;

    public boolean helloUser(Model model, String checkUseralias) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username.equals("anonymousUser") || username.equals("null")) {
            model.addAttribute("hellouseralias", "Welcome, visitor!");
            model.addAttribute("loggedinuser", "");
        } else {
            Principal principal = this.request.getUserPrincipal();
            String principalName = principal.getName();
            Account principalAccount = this.accountRepository.findByUsername(principalName);
            if (!principalAccount.getUseralias().equals(checkUseralias)) {
                model.addAttribute("date", this.date.date());
                return false;
            } else {
                String useralias = principalAccount.getUseralias();
                if (useralias.length() > 9) {
                    model.addAttribute("account", principalAccount);
                    model.addAttribute("date", this.date.date());
                    model.addAttribute("hellouseralias", "Hello, " + useralias.substring(0, 9) + "...!");
                    model.addAttribute("loggedinuser", useralias.substring(0, 9) + "...");
                    return true;
                } else {
                    model.addAttribute("account", principalAccount);
                    model.addAttribute("date", this.date.date());
                    model.addAttribute("hellouseralias", "Hello, " + useralias + " !");
                    model.addAttribute("loggedinuser", useralias);
                    return true;
                }
            }
        }
        return true;
    }
}
