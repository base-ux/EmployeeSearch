package projekti.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekti.domain.Post;
import projekti.logic.repository.PostsRepository;
import projekti.logic.utility.CustomDate;

@Service
public class PostsService {

    @Autowired
    CustomDate date;

    @Autowired
    private PostsRepository postsRepository;

    public void newPost(Post post, String useralias, String title, String message) {
        post = new Post();
        post.setUseralias(useralias);
        post.setPostingtime(this.date.dateTime());
        post.setTitle(title);
        post.setMessage(message);
        this.postsRepository.save(post);
    }
}
