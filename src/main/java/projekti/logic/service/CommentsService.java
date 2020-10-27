package projekti.logic.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projekti.domain.Account;
import projekti.domain.Comment;
import projekti.domain.Post;
import projekti.logic.repository.AccountRepository;
import projekti.logic.repository.CommentsRepository;
import projekti.logic.repository.PostsRepository;
import projekti.logic.utility.CustomDate;

@Service
public class CommentsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CustomDate date;

    // Deletes parameter comments from repository
    @Transactional
    public void deleteComments(List<Comment> comments) {
        for (Comment c : comments) {
            this.commentsRepository.delete(c);
        }
    }

    // After calling deleteComments method deletes parameter post from repository if post was created by parameter user
    @Transactional
    public void deletePost(String useralias, Post post) {
        if (post.getUseralias().equals(useralias)) {
            Pageable pageable = PageRequest.of(0, 25, Sort.by("postingtime").descending());
            List<Comment> comments = viewAllComments(post.getId(), pageable);
            deleteComments(comments);
            this.postsRepository.delete(post);
        }
    }

    // Returns the value (page number) of the last index in parameter list
    public Integer lastPage(List<Integer> totalPages) {
        int size = totalPages.size();
        Integer lastPage = totalPages.get(size - 1);
        switch (size) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return lastPage;
        }
    }

    public void newComment(Comment comment, Long postid, String useralias, String response) {
        comment = new Comment();
        Account account = this.accountRepository.findByUseralias(useralias);
        comment.setPostid(postid);
        comment.setUseralias(account.getUseralias());
        comment.setPostingtime(this.date.dateTime());
        comment.setResponse(response);
        this.commentsRepository.save(comment);
    }

    // Adds or removes user alias in the likers list of parameter post if it's not user's own post
    @Transactional
    public void likePost(String useralias, Post post) {
        if (!post.getUseralias().equals(useralias)) {
            List<String> likers = post.getLikers();
            if (!likers.contains(useralias)) {
                likers.add(useralias);
                post.setLikes(post.getLikes() + 1);
            } else {
                likers.remove(useralias);
                post.setLikes(post.getLikes() - 1);
            }
        }
    }

    // Returns a list of five page numbers for pagination
    public List<Integer> totalPages(int commentsPerPage, int showpagecomments, List<Comment> viewAllComments) {
        List<Integer> pageNumbers = new ArrayList<>();
        int totalComments = viewAllComments.size();
        if (totalComments > commentsPerPage) {
            int startPage = showpagecomments;
            int endPage = 0, additionalPages = 0, zeroPages = 0;
            int onePage = 1, twoPages = 2, threePages = 3, fourPages = 4;
            if (totalComments % commentsPerPage > zeroPages) {
                additionalPages = onePage;
            }
            int totalPages = totalComments / commentsPerPage + additionalPages;
            if (totalPages > startPage) {
                if (startPage + fourPages < totalPages) {
                    endPage = startPage + fourPages;
                } else if (startPage + fourPages < totalPages + onePage) {
                    endPage = startPage + threePages;
                    startPage = startPage - onePage;
                } else if (startPage + fourPages < totalPages + twoPages) {
                    endPage = startPage + twoPages;
                    startPage = startPage - twoPages;
                } else if (startPage + fourPages < totalPages + threePages) {
                    endPage = startPage + onePage;
                    startPage = startPage - threePages;
                } else if (startPage + fourPages < totalPages + fourPages) {
                    endPage = startPage;
                    startPage = startPage - fourPages;
                }
            }
            for (int i = startPage; i <= endPage; i++) {
                if (i < 0 || i == totalPages) {
                    continue;
                }
                pageNumbers.add(i);
            }
        } else {
            pageNumbers.add(0);
        }
        return pageNumbers;
    }

    public List<Comment> viewAllComments(Long postid, Pageable pageable) {
        List<Comment> viewAllComments = this.commentsRepository.findAllByPostid(postid, pageable);
        return viewAllComments;
    }
}
