package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private BlockService service;

    @PostMapping
    public BlockComponents saveAccount(@RequestBody Account account) throws ExecutionException, InterruptedException {
        return service.save(account, "Account");
    }

    @GetMapping
    public List<BlockComponents> getAllAccount() throws ExecutionException, InterruptedException {
        return service.getAll(Account.class, "Account");
    }

    @GetMapping("/{username}")
    public BlockComponents getAccount(@PathVariable String username) throws ExecutionException, InterruptedException {
        return service.getById(username, Account.class, "Account");
    }

    @PutMapping("/{username}")
    public BlockComponents updateAccount(@RequestBody Account account) throws ExecutionException, InterruptedException {
        return service.update(account, "Account");
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@PathVariable String username) throws ExecutionException, InterruptedException {
        return service.delete(username, "Account");
    }
}
