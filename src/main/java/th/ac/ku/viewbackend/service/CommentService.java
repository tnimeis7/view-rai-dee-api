package th.ac.ku.viewbackend.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.Comment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommentService {
    private static final String COLLECTION_NAME = "Comment";

    public String saveComment(Comment comment) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(comment.getCommentID()).set(comment);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Comment getComment(String commentID) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(COLLECTION_NAME).document(commentID);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        Comment comment;

        if(documentSnapshot.exists()){
            comment = documentSnapshot.toObject(Comment.class);
            return comment;
        }
        else{
            return null;
        }
    }

    public List<Comment> getAllComment() throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<Comment> commentList = new ArrayList<>();
        Comment comment;

        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            comment = documentSnapshot.toObject(Comment.class);
            commentList.add(comment);
        }
        return commentList;
    }

    public String updateComment(Comment comment) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(comment.getCommentID()).set(comment);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteComment(String commentID) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(commentID).delete();
        return "delete" + commentID + "successfully";
    }
}
