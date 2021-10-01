package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private BlockService service;

    @PostMapping
    public String saveAccount(@RequestBody Account user) throws ExecutionException, InterruptedException {
        return service.save(user, "Account");
    }

    @GetMapping
    public List<Class> getAllAccount() throws ExecutionException, InterruptedException {
        return service.getAll(Account.class, "Account");
    }

    @GetMapping("/{username}")
    public Class getAccount(@PathVariable String username) throws ExecutionException, InterruptedException {
        return service.getById(username, Account.class, "Account");
    }

    @PutMapping("/{username}")
    public String updateAccount(@RequestBody Account user) throws ExecutionException, InterruptedException {
        return service.update(user, "Account");
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@PathVariable String username) throws ExecutionException, InterruptedException {
        return service.delete(username, "Account");
    }
}
