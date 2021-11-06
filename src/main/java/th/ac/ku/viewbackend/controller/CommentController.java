package th.ac.ku.viewbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
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



}
