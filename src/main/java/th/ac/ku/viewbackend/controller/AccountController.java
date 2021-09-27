package th.ac.ku.viewbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.service.AccountService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public String saveAcc(@RequestBody Account acc) throws ExecutionException, InterruptedException {
        return accountService.saveAcc(acc);
    }

    @GetMapping
    public List<Account> getAllAcc() throws ExecutionException, InterruptedException {
        return accountService.getAllAcc();
    }

    @GetMapping("/{username}")
    public Account getAcc(@PathVariable String username) throws ExecutionException, InterruptedException {
        return accountService.getAcc(username);
    }

    @PutMapping("/{username}")
    public String updateAcc(@RequestBody Account user) throws ExecutionException, InterruptedException {
        return accountService.updateAcc(user);
    }

    @DeleteMapping("/{username}")
    public String deleteAcc(@PathVariable String username) throws ExecutionException, InterruptedException {
        return accountService.deleteAcc(username);
    }
}
