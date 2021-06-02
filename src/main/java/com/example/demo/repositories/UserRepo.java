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
public class UserRepo {
    @Autowired
    private FireBaseService fbase;
    private Firestore db;

    public UserRepo(){
        try {
            fbase = new FireBaseService();
            db = fbase.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers(){
        List<User> userList = new ArrayList<>();

        try {
            db = fbase.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("User").get();

            List<QueryDocumentSnapshot> documentss = null;
            documentss = future.get().getDocuments();
            if(userList.size()>0) userList.clear();
            for (DocumentSnapshot document : documentss) {
                User user =  document.toObject(User.class);
                userList.add(user);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("User").document(id);

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        return document.toObject(User.class);
    }

    public void addUser(User user){
        try {
            db = fbase.getFirestore();
            DocumentReference docRef = db.collection("User").document(user.getEmail());
            ApiFuture<WriteResult> result = docRef.set(user);
            System.out.println("Update time : " + result.get().getUpdateTime());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
