package projekti.logic.control;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Comment;
import projekti.domain.Post;
import projekti.logic.repository.CommentsRepository;
import projekti.logic.repository.PostsRepository;
import projekti.logic.service.CommentsService;
import projekti.logic.service.HomeService;

@Controller
public class CommentsController {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private PostsRepository postsRepository;

    // LOGGED IN
    // GET-REQUESTS
    @Secured("USER")
    @RequestMapping(value = "/EmployeeSearch/Users/{useralias}/Comments/{postid}", method = RequestMethod.GET)
    public String commentsWall(Model model, @ModelAttribute Comment comment,
            @PathVariable String useralias, @PathVariable Long postid,
            @RequestParam(defaultValue = "0") String showpagecomments) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            int commentsPerPage = 10;
            Pageable pageable = PageRequest.of(Integer.parseInt(showpagecomments), commentsPerPage, Sort.by("postingtime").descending());
            List<Integer> pages = this.commentsService.totalPages(commentsPerPage, Integer.parseInt(showpagecomments));
            Post post = this.postsRepository.getOne(postid);
            model.addAttribute("currentPageComments", Integer.parseInt(showpagecomments));
            model.addAttribute("lastPage", this.commentsService.lastPage(pages));
            model.addAttribute("post", post);
            model.addAttribute("postuseralias", post.getUseralias());
            model.addAttribute("totalPagesComments", pages);
            model.addAttribute("viewAllComments", this.commentsRepository.findAllByPostid(postid, pageable));
            return "comments";
        }
    }

    // LOGGED IN
    // POST-REQUESTS
    @Secured("USER")
    @RequestMapping(value = "/EmployeeSearch/Users/{useralias}/Comments/{postid}", method = RequestMethod.POST)
    public String commentNew(Model model, @Valid @ModelAttribute Comment comment,
            BindingResult bindingResult, @PathVariable String useralias,
            @PathVariable Long postid, @RequestParam(defaultValue = "0") String showpagecomments,
            @RequestParam String response) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            if (bindingResult.hasErrors()) {
                int commentsPerPage = 10;
                Pageable pageable = PageRequest.of(0, commentsPerPage, Sort.by("postingtime").descending());
                List<Integer> pages = this.commentsService.totalPages(commentsPerPage, Integer.parseInt(showpagecomments));
                model.addAttribute("currentPageComments", Integer.parseInt(showpagecomments));
                model.addAttribute("lastPage", this.commentsService.lastPage(pages));
                model.addAttribute("post", this.postsRepository.getOne(postid));
                model.addAttribute("totalPagesComments", pages);
                model.addAttribute("viewAllComments", this.commentsRepository.findAllByPostid(postid, pageable));
                return "comments";
            }
            this.commentsService.newComment(comment, postid, useralias, response);
            return "redirect:/EmployeeSearch/Users/" + useralias + "/Comments/" + postid;
        }
    }

    @Secured("USER")
    @RequestMapping(value = "/EmployeeSearch/Users/{useralias}/Comments/{postid}/LikeComment/{commentid}", method = RequestMethod.POST)
    public String likeComment(Model model, @ModelAttribute Comment comment,
            BindingResult bindingResult, @PathVariable String useralias,
            @PathVariable Long postid, @PathVariable Long commentid, @RequestParam(defaultValue = "0") String showpagecomments) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            this.commentsService.likeComment(useralias, this.commentsRepository.getOne(commentid));
            int commentsPerPage = 10;
            Pageable pageable = PageRequest.of(0, commentsPerPage, Sort.by("postingtime").descending());
            List<Integer> pages = this.commentsService.totalPages(commentsPerPage, Integer.parseInt(showpagecomments));
            model.addAttribute("currentPageComments", Integer.parseInt(showpagecomments));
            model.addAttribute("lastPage", this.commentsService.lastPage(pages));
            model.addAttribute("post", this.postsRepository.getOne(postid));
            model.addAttribute("totalPagesComments", pages);
            model.addAttribute("viewAllComments", this.commentsRepository.findAllByPostid(postid, pageable));
            return "redirect:/EmployeeSearch/Users/" + useralias + "/Comments/" + postid;
        }
    }

    @Secured("USER")
    @RequestMapping(value = "/EmployeeSearch/Users/{useralias}/Comments/{postid}/LikePost", method = RequestMethod.POST)
    public String likePost(Model model, @ModelAttribute Comment comment,
            BindingResult bindingResult, @PathVariable String useralias,
            @PathVariable Long postid, @RequestParam(defaultValue = "0") String showpagecomments) {
        if (this.homeService.helloUser(model, useralias) == false) {
            return "fragments/layout_address_error";
        } else {
            this.commentsService.likePost(useralias, this.postsRepository.getOne(postid));
            int commentsPerPage = 10;
            Pageable pageable = PageRequest.of(0, commentsPerPage, Sort.by("postingtime").descending());
            List<Integer> pages = this.commentsService.totalPages(commentsPerPage, Integer.parseInt(showpagecomments));
            model.addAttribute("currentPageComments", Integer.parseInt(showpagecomments));
            model.addAttribute("lastPage", this.commentsService.lastPage(pages));
            model.addAttribute("post", this.postsRepository.getOne(postid));
            model.addAttribute("totalPagesComments", pages);
            model.addAttribute("viewAllComments", this.commentsRepository.findAllByPostid(postid, pageable));
            return "redirect:/EmployeeSearch/Users/" + useralias + "/Comments/" + postid;
        }
    }
}
