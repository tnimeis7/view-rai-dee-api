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
import th.ac.ku.viewbackend.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GenreService {
    @Autowired
    private BlockService blockService;

    public List<BlockComponents> getGenreByAtcId(String atcId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("Genre").whereEqualTo("atcId", atcId).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<BlockComponents> genre = new ArrayList<>();
        for(DocumentSnapshot document: documents) {
            genre.add(document.toObject(Genre.class));
        }
        return genre;
    }

}
