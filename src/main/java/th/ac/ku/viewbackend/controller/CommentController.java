package th.ac.ku.viewbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.Comment;
import th.ac.ku.viewbackend.service.ArticleService;
import th.ac.ku.viewbackend.service.CommentService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public String saveComment(@RequestBody Comment comment) throws ExecutionException, InterruptedException {
        return commentService.saveComment(comment);
    }

    @GetMapping
    public List<Comment> getAllComment() throws ExecutionException, InterruptedException {
        return commentService.getAllComment();
    }

    @GetMapping("{commentID}")
    public Comment getComment(@RequestBody String commentID) throws ExecutionException, InterruptedException {
        return commentService.getComment(commentID);
    }

    @PutMapping
    public String updateComment(@RequestBody Comment comment) throws ExecutionException, InterruptedException {
        return commentService.updateComment(comment);
    }

    @DeleteMapping
    public String DeleteComment(@RequestBody String commentID) throws ExecutionException, InterruptedException {
        return commentService.deleteComment(commentID);
    }

}
