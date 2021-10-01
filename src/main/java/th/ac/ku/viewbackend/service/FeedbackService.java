package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.Feedback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FeedbackService {
    private static final String COLLECTION_NAME = "Feedback";

    public String saveFeedback(Feedback fb) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(fb.getFbId()).set(fb);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Feedback getFeedback(String fbId) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(COLLECTION_NAME).document(fbId);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        Feedback feedback = null;

        if(documentSnapshot.exists()){
            feedback = documentSnapshot.toObject(Feedback.class);
            return feedback;
        }
        else{
            return null;
        }
    }

    public List<Feedback> getAllFeedback() throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<Feedback> feedbackList = new ArrayList<>();
        Feedback feedback = null;

        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            feedback = documentSnapshot.toObject(Feedback.class);
            feedbackList.add(feedback);
        }
        return feedbackList;
    }

    public String updateFeedback(Feedback fb) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(fb.getFbId()).set(fb);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteFeedback(String fbId) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(fbId).delete();
        return "delete" + fbId + "successfully";
    }
}
