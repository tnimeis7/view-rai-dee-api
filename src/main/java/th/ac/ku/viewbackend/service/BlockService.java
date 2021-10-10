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

    public BlockComponents save(BlockComponents objectClass,String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collectionName).document(objectClass.getId()).set(objectClass);
        return objectClass;
    }

    public BlockComponents getById(String Id, Class objectClass, String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(collectionName).document(Id);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        BlockComponents object;
        if(documentSnapshot.exists()){
            object = (BlockComponents) documentSnapshot.toObject(objectClass);
            return object;
        }
        else{
            return null;
        }
    }

    public List<BlockComponents> getAll(Class objectClass,String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(collectionName).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<BlockComponents> classList = new ArrayList<>();
        BlockComponents object;
        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            object = (BlockComponents) documentSnapshot.toObject(objectClass);
            classList.add(object);
        }
        return classList;
    }

    public BlockComponents update(BlockComponents objectClass,String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collectionName).document(objectClass.getId()).set(objectClass);
        return objectClass;
    }

    public String delete(String commentID, String collectionName) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(collectionName).document(commentID).delete();
        return "delete" + commentID + "successfully";
    }
}
