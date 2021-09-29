package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.StreamingPlatform;
import th.ac.ku.viewbackend.service.ArticleService;
import th.ac.ku.viewbackend.service.StreamingPlatformService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Stream")
public class StreamingPlatformController {

    @Autowired
    private StreamingPlatformService streamingPlatformService;

    @PostMapping
    public String savePlatform(@RequestBody StreamingPlatform streamingPlatform) throws ExecutionException, InterruptedException {
        return streamingPlatformService.savePlatform(streamingPlatform);
    }

    @GetMapping
    public List<StreamingPlatform> getAllPlatform() throws ExecutionException, InterruptedException {
        return streamingPlatformService.getAllPlatform();
    }

    @GetMapping("/{platformName}")
    public StreamingPlatform getPlatform(@PathVariable String platformName) throws ExecutionException, InterruptedException {
        return streamingPlatformService.getPlatform(platformName);
    }

    @PutMapping("/{platformName}")
    public String updatePlatform(@RequestBody StreamingPlatform streamingPlatform) throws ExecutionException, InterruptedException {
        return streamingPlatformService.updatePlatform(streamingPlatform);
    }

    @DeleteMapping("/{platformName}")
    public String deletePlatform(@PathVariable String platformName) throws ExecutionException, InterruptedException {
        return streamingPlatformService.deletePlatform(platformName);
    }
}
