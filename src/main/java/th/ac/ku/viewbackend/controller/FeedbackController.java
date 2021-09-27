package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.Feedback;
import th.ac.ku.viewbackend.service.ArticleService;
import th.ac.ku.viewbackend.service.FeedbackService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public String saveFb(@RequestBody Feedback fb) throws ExecutionException, InterruptedException {
        return feedbackService.saveFeedback(fb);
    }

    @GetMapping
    public List<Feedback> getAllFb() throws ExecutionException, InterruptedException {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("{fbId}")
    public Feedback getFb(@RequestBody String fbId) throws ExecutionException, InterruptedException {
        return feedbackService.getFeedback(fbId);
    }

    @PutMapping
    public String updateFb(@RequestBody Feedback fb) throws ExecutionException, InterruptedException {
        return feedbackService.updateFeedback(fb);
    }

    @DeleteMapping
    public String DeleteFb(@RequestBody String fbId) throws ExecutionException, InterruptedException {
        return feedbackService.deleteFeedback(fbId);
    }
}
