package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.service.ArticleService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public String saveAtc(@RequestBody Article atc) throws ExecutionException, InterruptedException {
        return articleService.saveArticle(atc);
    }

    @GetMapping
    public List<Article> getAllAtc() throws ExecutionException, InterruptedException {
        return articleService.getAllArticle();
    }

    @GetMapping("/{atcId}")
    public Article getAtc(@PathVariable String atcId) throws ExecutionException, InterruptedException {
        return articleService.getArticle(atcId);
    }

    @PutMapping("/{atcId}")
    public String updateAtc(@RequestBody Article atc) throws ExecutionException, InterruptedException {
        return articleService.updateArticle(atc);
    }

    @DeleteMapping("/{atcId}")
    public String deleteAtc(@PathVariable String atcId) throws ExecutionException, InterruptedException {
        return articleService.deleteArticle(atcId);
    }

}
