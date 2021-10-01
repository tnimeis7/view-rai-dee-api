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
    public List<Tag> getAllTag() throws ExecutionException, InterruptedException {
        return tagService.getAllTag();
    }

    @GetMapping("{tagID}")
    public Tag getTag(@PathVariable String tagID) throws ExecutionException, InterruptedException {
        return tagService.getTag(tagID);
    }

    @DeleteMapping("{tagID}")
    public String deleteTag(@PathVariable String tagID) throws ExecutionException, InterruptedException {
        return tagService.deleteTag(tagID);
    }
}
