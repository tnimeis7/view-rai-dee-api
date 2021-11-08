package th.ac.ku.viewbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Comment;
import th.ac.ku.viewbackend.service.BlockService;
import th.ac.ku.viewbackend.service.CommentService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public BlockComponents saveComment(@RequestBody Comment comment) throws ExecutionException, InterruptedException {
        comment.setCommentDate();
        return commentService.save(comment);
    }

    @GetMapping
    public List<BlockComponents> getAllComment() throws ExecutionException, InterruptedException {
        return commentService.getAll();
    }

    @GetMapping("/username/{name}")
    public List<BlockComponents> getCommentByUsername(@PathVariable String name) throws ExecutionException, InterruptedException {
        return commentService.getCommentByUsername(name);
    }

    @GetMapping("/{commentId}")
    public BlockComponents getComment(@PathVariable String commentId) throws ExecutionException, InterruptedException {
        return commentService.getById(commentId);
    }

    @GetMapping("/articleId/{articleId}")
    public List<BlockComponents> getCommentByArticleId(@PathVariable String articleId) throws ExecutionException, InterruptedException {
        return commentService.getCommentByAtcId(articleId);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable String commentId) throws ExecutionException, InterruptedException {
        return commentService.delete(commentId);
    }

    @PostMapping("/setUsername/{username}/{newUsername}")
    public void setCommentUsername(@PathVariable String username,@PathVariable String newUsername) throws ExecutionException, InterruptedException {
        List<BlockComponents> comments = getCommentByUsername(username);
        for (BlockComponents var: comments) {
            Comment c = (Comment) var;
            c.setUsername(newUsername);
            commentService.update(c);
        }
    }

    @PostMapping("/delete/{username}")
    public void setCommentUsername(@PathVariable String username) throws ExecutionException, InterruptedException {
        List<BlockComponents> comments = getCommentByUsername(username);
        for (BlockComponents var: comments) {
            Comment c = (Comment) var;
            deleteComment(c.getId());
        }
    }



}
