package projekti.logic.control;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Post;
import projekti.logic.repository.PostsRepository;
import projekti.logic.service.HomeService;
import projekti.logic.service.PostsService;

@Controller
public class PostsController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsService postsService;

    // LOGGED IN
    // GET-REQUESTS
    @Secured("USER")
    @GetMapping("/EmployeeSearch/Users/{useralias}/Posts")
    public String postsWall(Model model, @ModelAttribute Post post,
            @PathVariable String useralias) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            Pageable pageable = PageRequest.of(0, 25, Sort.by("postingtime").descending());
            model.addAttribute("viewAllPosts", this.postsRepository.findAll(pageable));
            System.out.println("size: " + this.postsRepository.findAll().size());
            return "posts";
        }
    }

    // LOGGED IN
    // POST-REQUESTS
    @Secured("USER")
    @PostMapping("/EmployeeSearch/Users/{useralias}/Posts")
    public String postNew(Model model, @Valid @ModelAttribute Post post,
            BindingResult bindingResult, @PathVariable String useralias,
            @RequestParam String title, @RequestParam String message) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (bindingResult.hasErrors()) {
                Pageable pageable = PageRequest.of(0, 25, Sort.by("postingtime").descending());
                model.addAttribute("viewAllPosts", this.postsRepository.findAll(pageable));
                return "posts";
            }
            this.postsService.newPost(post, useralias, title, message);
            return "redirect:/EmployeeSearch/Users/" + useralias + "/Posts";
        }
    }
}
