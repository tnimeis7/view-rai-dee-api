package th.ac.ku.viewbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Comment;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Comment")
public class CommentController {

    @Autowired
    private BlockService service;

    @PostMapping
    public String saveComment(@RequestBody Comment comment) throws ExecutionException, InterruptedException {
        return service.save(comment, "Comment");
    }

    @GetMapping
    public List<BlockComponents> getAllComment() throws ExecutionException, InterruptedException {
        return service.getAll(Comment.class, "Comment");
    }

    @GetMapping("/{commentId}")
    public BlockComponents getComment(@PathVariable String commentId) throws ExecutionException, InterruptedException {
        return service.getById(commentId, Comment.class, "Comment");
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable String commentId) throws ExecutionException, InterruptedException {
        return service.delete(commentId, "Comment");
    }

}
