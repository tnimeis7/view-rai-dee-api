package th.ac.ku.viewbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.service.ArticleService;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Article")
public class ArticleController {

    @Autowired
    private BlockService service;

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public BlockComponents saveArticle(@RequestBody Article article) throws ExecutionException, InterruptedException {
        return service.save(article, "Article");
    }

    @GetMapping
    public List<BlockComponents> getAllArticle() throws ExecutionException, InterruptedException {
        return service.getAll(Article.class, "Article");
    }

    @GetMapping("/{atcId}")
    public BlockComponents getArticle(@PathVariable String atcId) throws ExecutionException, InterruptedException {
        return service.getById(atcId, Article.class, "Article");
    }


    @DeleteMapping("/{atcId}")
    public String deleteArticle(@PathVariable String atcId) throws ExecutionException, InterruptedException {
        return service.delete(atcId, "Article");
    }

    @GetMapping("/MostPopular")
    public List<BlockComponents> getAllArticlePopular() throws ExecutionException, InterruptedException {
        return articleService.getPopularArticles();
    }

    @GetMapping("/MostPopular/{type}")
    public List<Article> getAllArticlePopularByType(@PathVariable String type) throws ExecutionException, InterruptedException{
        return articleService.getPopularArticlesByType(type);
    }

}
