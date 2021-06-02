package com.example.demo.models;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Component
public class FireBaseService {

    public FireBaseService() throws IOException {
        //getting file from resource folder
//        Resource resource = new ClassPathResource("key.json");
//        InputStream inputStream = resource.getInputStream();
//        File file = resource.getFile();

//        InputStream serviceAccount = new FileInputStream("resources\\key.json");
        InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("key.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

    }

    public Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }


}
