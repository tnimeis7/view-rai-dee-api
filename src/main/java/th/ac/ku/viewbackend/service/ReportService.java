package th.ac.ku.viewbackend.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import th.ac.ku.viewbackend.model.Article;
import th.ac.ku.viewbackend.model.Report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReportService {

    private static final String COLLECTION_NAME = "Report";

    public String saveReport(Report report) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(report.getReportId()).set(report);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Report getReport(String reportId) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReferences = dbFirestore.collection(COLLECTION_NAME).document(reportId);
        ApiFuture<DocumentSnapshot> future = documentReferences.get();
        DocumentSnapshot documentSnapshot = future.get();
        Report report = null;

        if(documentSnapshot.exists()){
            report = documentSnapshot.toObject(Report.class);
            return report;
        }
        else{
            return null;
        }
    }

    public List<Report> getAllReport() throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReferences.iterator();
        List<Report> reportList = new ArrayList<>();
        Report report = null;

        while(iterator.hasNext()){
            DocumentReference documentReference = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            report = documentSnapshot.toObject(Report.class);
            reportList.add(report);
        }
        return reportList;
    }

    public String updateReport(Report report) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(report.getReportId()).set(report);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String deleteReport(String reportId) throws ExecutionException, InterruptedException{

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(reportId).delete();
        return "delete" + reportId + "successfully";
    }
}
