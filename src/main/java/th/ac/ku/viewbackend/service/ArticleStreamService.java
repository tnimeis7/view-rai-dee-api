package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.ArticleStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ArticleStreamService {
    private static final String COLLECTION_NAME = "ArticleStream";

    public String saveAtcStream(ArticleStream articleStream) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document((articleStream.getAtcId()+"_"+articleStream.getPlatform())).set(articleStream);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public ArticleStream getAtcStream(String atcStreamId) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(COLLECTION_NAME).document(atcStreamId);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        ArticleStream articleStream = null;

        if(documentSnapshot.exists()){
            articleStream = documentSnapshot.toObject(ArticleStream.class);
            return articleStream;
        }
        else{
            return null;
        }
    }

    public List<ArticleStream> getAllAtcStream() throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<ArticleStream> articleList = new ArrayList<>();
        ArticleStream articleStream = null;

        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            articleStream = documentSnapshot.toObject(ArticleStream.class);
            articleList.add(articleStream);
        }
        return articleList;
    }

    public String deleteAtcStream(String atcStreamId) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(atcStreamId).delete();
        return "delete " + atcStreamId + " successfully";
    }
}
