package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.service.AccountService;
import th.ac.ku.viewbackend.service.BlockService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public BlockComponents saveAccount(@RequestBody Account account) throws ExecutionException, InterruptedException {
        return service.save(account);
    }

    @GetMapping
    public List<BlockComponents> getAllAccount() throws ExecutionException, InterruptedException {
        return service.getAll();
    }

    @GetMapping("/id/{username}")
    public BlockComponents getAccount(@PathVariable String username) throws ExecutionException, InterruptedException {
        return service.getById(username);
    }

    @GetMapping("/email/{email}")
    public BlockComponents getAccountByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        return service.getByEmail(email);
    }

    @PutMapping("/{username}")
    public BlockComponents updateAccount(@RequestBody Account account) throws ExecutionException, InterruptedException {
        return service.update(account);
    }

    @PostMapping("/heart/{username}")
    public BlockComponents plusHeartUser(@RequestBody String username) throws ExecutionException, InterruptedException {
        Account account = (Account) getAccount(username);
        account.setCountHeart(account.getCountHeart()+1);
        return service.update(account);
    }

    @DeleteMapping("/{username}")
    public String deleteAccount(@PathVariable String username) throws ExecutionException, InterruptedException {
        return service.delete(username);
    }
}
