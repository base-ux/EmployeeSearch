package projekti.logic.control;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourceErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @GetMapping("/error")
    public String error() {
        return "resource_error";
    }
}
