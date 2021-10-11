package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.Account;
import th.ac.ku.viewbackend.model.BlockComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AccountService {
    @Autowired
    private BlockService blockService;

    public BlockComponents save(Account account) throws ExecutionException, InterruptedException{
        return blockService.save(account , "Account");
    }

    public List<BlockComponents> getAll() throws ExecutionException, InterruptedException{
        return blockService.getAll(Account.class, "Account");
    }

    public BlockComponents getById(String Id) throws ExecutionException, InterruptedException{
        return blockService.getById(Id, Account.class , "Account");
    }

    public BlockComponents update(Account account) throws ExecutionException, InterruptedException{
        return blockService.update(account, "Account");
    }

    public String delete(String id) throws ExecutionException, InterruptedException{
        return blockService.delete(id, "Account");
    }
    public BlockComponents getByEmail(String email) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference cities = dbFirestore.collection("Account");
        Query query = cities.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> future = query.get();
        DocumentSnapshot documentSnapshot = (DocumentSnapshot) future.get().getDocuments();
        BlockComponents object;
        if(documentSnapshot.exists()){
            object = (BlockComponents) documentSnapshot.toObject(Account.class);
            return object;
        }
        else{
            return null;
        }
    }
}
