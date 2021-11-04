package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TagService {
    @Autowired
    private BlockService blockService;

    public List<BlockComponents> getTagsByAtcId(String atcId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("Tag").whereEqualTo("atcId", atcId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<BlockComponents> tags = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            tags.add(document.toObject(Tag.class));
        }
        return tags;
    }

}
