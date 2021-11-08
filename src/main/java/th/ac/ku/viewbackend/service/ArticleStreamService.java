package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.ArticleStream;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Genre;
import th.ac.ku.viewbackend.model.StreamingPlatform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ArticleStreamService {
    @Autowired
    private BlockService blockService;

    public List<BlockComponents> getNamePfByAtcId(String atcId) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("ArticleStream").whereEqualTo("atcId", atcId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<ArticleStream> blockComponents = new ArrayList<>();
        List<BlockComponents> result = new ArrayList<>();
        BlockComponents object = null;
        int i = 0;
        for (DocumentSnapshot document : documents){
            blockComponents.add(document.toObject(ArticleStream.class));
            object = blockService.getById(blockComponents.get(i).getPlatform(), StreamingPlatform.class, "StreamingPlatform");
            result.add(object);
            i++;
        }
        return result;
    }

    public List<String> getAtcIdByPfName(String pfName) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("ArticleStream").whereEqualTo("platform", pfName).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<String> atcId = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            atcId.add(document.toObject(Genre.class).getAtcId());
        }
        return atcId;
    }
}
