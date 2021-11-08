package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.BlockComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ArticleService {
    @Autowired
    private BlockService blockService;

    public List<BlockComponents> getPopularArticles() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("Article").orderBy("heart", Query.Direction.DESCENDING).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<BlockComponents> mostHeart = new ArrayList<>();
        for(DocumentSnapshot document: documents) {
            mostHeart.add(document.toObject(Article.class));
        }
        return mostHeart;
    }

    public List<BlockComponents> getArticlesByAuthorName(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("Article").whereEqualTo("authorName", name).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<BlockComponents> mostHeart = new ArrayList<>();
        for(DocumentSnapshot document: documents) {
            mostHeart.add(document.toObject(Article.class));
        }
        return mostHeart;
    }


}
