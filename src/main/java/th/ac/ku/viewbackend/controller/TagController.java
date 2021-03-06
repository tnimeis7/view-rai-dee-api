package th.ac.ku.viewbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.StreamingPlatform;
import th.ac.ku.viewbackend.model.Tag;
import th.ac.ku.viewbackend.service.BlockService;
import th.ac.ku.viewbackend.service.TagService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Tag")
public class TagController {

    @Autowired
    private BlockService service;

    @Autowired
    private TagService tagService;

    @PostMapping
    public BlockComponents saveTag(@RequestBody Tag tag) throws ExecutionException, InterruptedException {
        return service.save(tag, "Tag");
    }

    @GetMapping
    public List<BlockComponents> getAllTag() throws ExecutionException, InterruptedException {
        return service.getAll(Tag.class, "Tag");
    }

    @GetMapping("/{tagId}")
    public BlockComponents getTag(@PathVariable String tagId) throws ExecutionException, InterruptedException {
        return service.getById(tagId, Tag.class, "Tag");
    }

    @DeleteMapping("/{tagId}")
    public String deleteTag(@PathVariable String tagId) throws ExecutionException, InterruptedException {
        return service.delete(tagId, "Tag");
    }

    @GetMapping("/{atcId}/tag")
    public List<BlockComponents> getTagsByAtcId(@PathVariable String atcId) throws ExecutionException, InterruptedException{
        return tagService.getTagsByAtcId(atcId);
    }

}
