package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.ArticleStream;
import th.ac.ku.viewbackend.service.ArticleStreamService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/ArticleStream")
public class ArticleStreamController {

    @Autowired
    private ArticleStreamService articleStreamService;

    @PostMapping
    public String saveAtc(@RequestBody ArticleStream articleStream) throws ExecutionException, InterruptedException {
        return articleStreamService.saveAtcStream(articleStream);
    }

    @GetMapping
    public List<ArticleStream> getAllAtc() throws ExecutionException, InterruptedException {
        return articleStreamService.getAllAtcStream();
    }

    @GetMapping("/{atcStreamId}")
    public ArticleStream getAtc(@PathVariable String atcStreamId) throws ExecutionException, InterruptedException {
        return articleStreamService.getAtcStream(atcStreamId);
    }

    @DeleteMapping("/{atcStreamId}")
    public String deleteAtc(@PathVariable String atcStreamId) throws ExecutionException, InterruptedException {
        return articleStreamService.deleteAtcStream(atcStreamId);
    }

}
