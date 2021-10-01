package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.BlockComponents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BlockService {

    public String save(BlockComponents objectClass,String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collectionName).document(objectClass.getId()).set(objectClass);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Class getById(String Id, Class objectClass,String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(collectionName).document(Id);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        Class object;
        if(documentSnapshot.exists()){
            object = (Class) documentSnapshot.toObject(objectClass);
            return object;
        }
        else{
            return null;
        }
    }

    public List<Class> getAll(Class objectClass,String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(collectionName).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<Class> classList = new ArrayList<>();
        Class object;
        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            object = (Class) documentSnapshot.toObject(objectClass);
            classList.add(object);
        }
        return classList;
    }

    public String update(BlockComponents objectClass,String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collectionName).document(objectClass.getId()).set(objectClass);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String delete(String commentID, String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collectionName).document(commentID).delete();
        return "delete" + commentID + "successfully";
    }
}
