package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Comment;
import th.ac.ku.viewbackend.model.Feedback;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Feedback")
public class FeedbackController {

    @Autowired
    private BlockService service;

    @PostMapping
    public String saveFeedback(@RequestBody Feedback feedback) throws ExecutionException, InterruptedException {
        return service.save(feedback, "Feedback");
    }

    @GetMapping
    public List<BlockComponents> getAllFeedback() throws ExecutionException, InterruptedException {
        return service.getAll(Feedback.class, "Feedback");
    }

    @GetMapping("/{fbId}")
    public BlockComponents getFeedback(@PathVariable String fbId) throws ExecutionException, InterruptedException {
        return service.getById(fbId, Feedback.class, "Feedback");
    }

    @PutMapping("/{fbId}")
    public String updateFeedback(@RequestBody Feedback feedback) throws ExecutionException, InterruptedException {
        return service.update(feedback, "Feedback");
    }

    @DeleteMapping("/{fbId}")
    public String deleteFeedback(@PathVariable String fbId) throws ExecutionException, InterruptedException {
        return service.delete(fbId, "Feedback");
    }
}
