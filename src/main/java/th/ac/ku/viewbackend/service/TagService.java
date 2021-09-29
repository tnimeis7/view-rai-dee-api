package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.Tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TagService {

    private static final String COLLECTION_NAME = "Tag";

    public String saveTag(Tag tags) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(tags.getNameTag()+"_"+tags.getAtcId()).set(tags);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Tag getTag(String nameTag) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(COLLECTION_NAME).document(nameTag);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        Tag tagTag = null;

        if(documentSnapshot.exists()){
            tagTag = documentSnapshot.toObject(Tag.class);
            return tagTag;
        }
        else{
            return null;
        }
    }

    public List<Tag> getAllTag() throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<Tag> articleList = new ArrayList<>();
        Tag tagTag = null;

        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            tagTag = documentSnapshot.toObject(Tag.class);
            articleList.add(tagTag);
        }
        return articleList;
    }


    public String deleteArticle(String nameTag) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(nameTag).delete();
        return "delete " + nameTag + " successfully";
    }
}
