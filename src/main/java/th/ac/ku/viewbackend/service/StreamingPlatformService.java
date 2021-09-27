package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.StreamingPlatform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StreamingPlatformService {
    private static final String COLLECTION_NAME = "StreamingPlatform";

    public String savePlatform(StreamingPlatform streamingPlatform) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(streamingPlatform.getPlatformName()).set(streamingPlatform);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public StreamingPlatform getPlatform(String platformName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(COLLECTION_NAME).document(platformName);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        StreamingPlatform streamingPlatform = null;

        if(documentSnapshot.exists()){
            streamingPlatform = documentSnapshot.toObject(StreamingPlatform.class);
            return streamingPlatform;
        }
        else{
            return null;
        }
    }

    public List<StreamingPlatform> getAllPlatform() throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<StreamingPlatform> platformList = new ArrayList<>();
        StreamingPlatform streamingPlatform = null;

        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            streamingPlatform = documentSnapshot.toObject(StreamingPlatform.class);
            platformList.add(streamingPlatform);
        }
        return platformList;
    }

    public String updatePlatform(StreamingPlatform streamingPlatform) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(streamingPlatform.getPlatformName()).set(streamingPlatform);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deletePlatform(String platformName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(platformName).delete();
        return "delete " + platformName + " successfully";
    }
}
