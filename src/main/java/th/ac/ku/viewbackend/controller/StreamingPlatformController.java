package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Report;
import th.ac.ku.viewbackend.model.StreamingPlatform;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Stream")
public class StreamingPlatformController {

    @Autowired
    private BlockService service;

    @PostMapping
    public BlockComponents saveStreamingPlatform(@RequestBody StreamingPlatform streamingPlatform) throws ExecutionException, InterruptedException {
        return service.save(streamingPlatform, "StreamingPlatform");
    }

    @GetMapping
    public List<BlockComponents> getAllStreamingPlatform() throws ExecutionException, InterruptedException {
        return service.getAll(StreamingPlatform.class, "StreamingPlatform");
    }

    @GetMapping("/{platformName}")
    public BlockComponents getStreamingPlatform(@PathVariable String platformName) throws ExecutionException, InterruptedException {
        return service.getById(platformName, StreamingPlatform.class, "StreamingPlatform");
    }

    @PutMapping("/{platformName}")
    public BlockComponents updateStreamingPlatform(@RequestBody StreamingPlatform streamingPlatform) throws ExecutionException, InterruptedException {
        return service.update(streamingPlatform, "StreamingPlatform");
    }

    @DeleteMapping("/{platformName}")
    public String deleteStreamingPlatform(@PathVariable String platformName) throws ExecutionException, InterruptedException {
        return service.delete(platformName, "StreamingPlatform");
    }
}
