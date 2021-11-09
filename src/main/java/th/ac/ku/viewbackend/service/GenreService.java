package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.BlockComponents;
import th.ac.ku.viewbackend.model.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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

    public List<String> getAllGenreName() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("Genre").select("genreName").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<String> genre = new ArrayList<>();
        for(DocumentSnapshot document: documents) {
            genre.add(document.toObject(Genre.class).getGenreName());
        }
        return genre.stream().distinct().collect(Collectors.toList());
    }

    public List<String> getAtcIdByGenre(String genreName) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection("Genre").whereEqualTo("genreName", genreName).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<String> atcId = new ArrayList<>();
        for (DocumentSnapshot document : documents){
            atcId.add(document.toObject(Genre.class).getAtcId());
        }
        return atcId;
    }
}
