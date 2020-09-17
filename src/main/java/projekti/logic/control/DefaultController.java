package projekti.logic.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String root() {
        return "redirect:/EmployeeSearch/Welcome";
    }

    @GetMapping("/EmployeeSearch")
    public String rootEmployeeSearchA() {
        return "redirect:/EmployeeSearch/Welcome";
    }

    @GetMapping("/EmployeeSearch/")
    public String rootEmployeeSearchB() {
        return "redirect:/EmployeeSearch/Welcome";
    }
}
