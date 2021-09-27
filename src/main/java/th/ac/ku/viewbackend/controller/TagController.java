package th.ac.ku.viewbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Tag;
import th.ac.ku.viewbackend.service.TagService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping
    public String saveTag(@RequestBody Tag tags) throws ExecutionException, InterruptedException {
        return tagService.saveTag(tags);
    }

    @GetMapping
    public List<Tag> getAllAtc() throws ExecutionException, InterruptedException {
        return tagService.getAllTag();
    }

    @GetMapping("{nameTag}")
    public Tag getTag(@PathVariable String nameTag) throws ExecutionException, InterruptedException {
        return tagService.getTag(nameTag);
    }

    @PutMapping
    public String updateTag(@RequestBody Tag tags) throws ExecutionException, InterruptedException {
        return tagService.updateTag(tags);
    }

    @DeleteMapping
    public String deleteTag(@PathVariable String nameTag) throws ExecutionException, InterruptedException {
        return tagService.deleteArticle(nameTag);
    }
}
