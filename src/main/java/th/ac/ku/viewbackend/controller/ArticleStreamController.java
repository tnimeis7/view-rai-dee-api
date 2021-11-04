package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.ArticleStream;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.service.ArticleStreamService;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/ArticleStream")
public class ArticleStreamController {

    @Autowired
    private BlockService service;

    @Autowired
    private ArticleStreamService articleStreamService;

    @PostMapping
    public BlockComponents saveArticleStream(@RequestBody ArticleStream articleStream) throws ExecutionException, InterruptedException {
        return service.save(articleStream, "ArticleStream");
    }

    @GetMapping
    public List<BlockComponents> getAllArticleStream() throws ExecutionException, InterruptedException {
        return service.getAll(ArticleStream.class, "ArticleStream");
    }

    @GetMapping("/{atcStreamId}")
    public BlockComponents getArticleStream(@PathVariable String atcStreamId) throws ExecutionException, InterruptedException {
        return service.getById(atcStreamId, ArticleStream.class, "ArticleStream");
    }

    @DeleteMapping("/{atcStreamId}")
    public String deleteArticleStream(@PathVariable String atcStreamId) throws ExecutionException, InterruptedException {
        return service.delete(atcStreamId, "ArticleStream");
    }

    @GetMapping("/{atcId}/platform")
    public List<BlockComponents> getAllPlatformByAtcId(@PathVariable String atcId) throws ExecutionException, InterruptedException {
        return articleStreamService.getNamePfByAtcId(atcId);
    }

}
