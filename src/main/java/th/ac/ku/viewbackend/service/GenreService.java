package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.Genre;
import th.ac.ku.viewbackend.model.Tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GenreService {

    private static final String COLLECTION_NAME = "Genre";

    public String saveGenre(Genre genre) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(genre.getGenreName()+"_"+genre.getAtcId()).set(genre);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Genre getGenre(String genreDocId) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(COLLECTION_NAME).document(genreDocId);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        Genre gen = null;

        if(documentSnapshot.exists()){
            gen= documentSnapshot.toObject(Genre.class);
            return gen;
        }
        else{
            return null;
        }
    }

    public List<Genre> getAllGenre() throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<Genre> genreList = new ArrayList<>();
        Genre gen = null;

        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            gen = documentSnapshot.toObject(Genre.class);
            genreList.add(gen);
        }
        return genreList;
    }


    public String deleteGenre(String genreDocId) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(genreDocId).delete();
        return "delete " + genreDocId + " successfully";
    }

}
