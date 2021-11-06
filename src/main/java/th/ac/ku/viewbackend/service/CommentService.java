package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommentService {

    @Autowired
    private BlockService blockService;

    public BlockComponents save(Comment comment) throws ExecutionException, InterruptedException{
        return blockService.save(comment , "Comment");
    }

    public List<BlockComponents> getAll() throws ExecutionException, InterruptedException{
        return blockService.getAll(Comment.class, "Comment");
    }

    public BlockComponents getById(String Id) throws ExecutionException, InterruptedException{
        return blockService.getById(Id, Comment.class , "Comment");
    }

    public String delete(String id) throws ExecutionException, InterruptedException{
        return blockService.delete(id, "Comment");
    }

    public List<BlockComponents> getCommentByAtcId(String articleId) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("Comment").whereEqualTo("articleId", articleId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Comment> blockComponents = new ArrayList<>();
        List<BlockComponents> result = new ArrayList<>();
        BlockComponents object = null;
        int i = 0;
        for (DocumentSnapshot document : documents){
            blockComponents.add(document.toObject(Comment.class));
            object = blockService.getById(blockComponents.get(i).getId(), Comment.class, "Comment");
            result.add(object);
            i++;
        }
        return result;
    }
}
