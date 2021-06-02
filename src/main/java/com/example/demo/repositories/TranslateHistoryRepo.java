package com.example.demo.repositories;

import com.example.demo.models.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class TranslateHistoryRepo {
    @Autowired
    private FireBaseService fbase;
    private Firestore db;

    public TranslateHistoryRepo() {
        try {
            fbase = new FireBaseService();
            db = fbase.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TranslateHistory> getTranslateHistoryOfUser(String authorEmail) {
        List<TranslateHistory> historyList = new ArrayList<>();
        try {
            db = fbase.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("TranslateHistory").get();

            List<QueryDocumentSnapshot> documents = null;
            documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                TranslateHistory translateHistory = document.toObject(TranslateHistory.class);
                assert translateHistory != null;
                if (translateHistory.getAuthorEmail().equals(authorEmail)) {
                    historyList.add(translateHistory);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return historyList;
    }

    public void addTranslateHistory(TranslateHistory history) {
        try {
            db = fbase.getFirestore();
            DocumentReference ref = db.collection("TranslateHistory").document();
            String id = ref.getId();
            System.out.println(
                    id
            );
            history.setId(id);
            DocumentReference docRef = db.collection("TranslateHistory").document(id);
            ApiFuture<WriteResult> result = docRef.set(history);
            System.out.println("Update time : " + result.get().getUpdateTime());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
