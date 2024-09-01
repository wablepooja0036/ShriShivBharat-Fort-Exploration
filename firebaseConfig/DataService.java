package com.c2w.firebaseConfig;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.internal.NonNull;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import com.google.cloud.firestore.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DataService {

    private static Firestore db;

    static {
        try {
            initializeFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeFirebase() throws IOException {

        FileInputStream serviceAccount = new FileInputStream("shrishivbharat\\src\\main\\resources\\fortsapplication-firebase-adminsdk-y8gob-bb1449755f.json");

        @SuppressWarnings("deprecation")
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://fortsapplication-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();

    }

    public void addData(String collection, String document, Map<String, Object> data)
            throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collection).document(document);
        ApiFuture<WriteResult> result = docRef.set(data);
        result.get(); // Block until complete
    }

    public DocumentSnapshot getData(String collection, String document)
            throws ExecutionException, InterruptedException {
        try {
            DocumentReference docRef = db.collection(collection).document(document);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            return future.get();
        } catch (Exception e) {

            e.printStackTrace(); // Example: Print the stack trace for debugging
            throw e; // Re-throw the exception or handle it based on your application's needs
        }
    }

    public boolean authenticateUser(String username, String password) throws ExecutionException, InterruptedException {
        DocumentSnapshot document = db.collection("users").document(username).get().get();
        if (document.exists()) {
            String storedPassword = document.getString("password");
            return password.equals(storedPassword);
        }
        return false;
    }

    public boolean isAdmin(String username) throws ExecutionException,
            InterruptedException {
        DocumentSnapshot document = db.collection("users").document(username).get().get();
        if (document.exists()) {
            String role = document.getString("role");
            System.out.println(role);
            return "admin".equals(role);
        }

        return false;
    }


public void deleteProject(String collectionName, String leaderName) throws ExecutionException, InterruptedException {
    // Create a query to find the document where the "Name" field matches leaderName
    ApiFuture<QuerySnapshot> future = db.collection(collectionName)
                                       .whereEqualTo("Name", leaderName)
                                       .get();

    // Query the database
    List<QueryDocumentSnapshot> documents = future.get().getDocuments();

    // Check if any documents are returned and delete the first match (assuming unique "Name" field)
    if (!documents.isEmpty()) {
        for (QueryDocumentSnapshot document : documents) {
            ApiFuture<WriteResult> writeResult = db.collection(collectionName)
                                                   .document(document.getId())
                                                   .delete();
            writeResult.get();
        }
       showAlert(AlertType.INFORMATION, "Success", "Document(s) deleted successfully.");
    } else {
        System.out.println("No document found with the given Name.");
    }
}
private void showAlert(AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


    public List<Map<String, Object>> getDataInDescendingOrder(String collectionName,
            String orderByField) throws ExecutionException, InterruptedException {
        List<Map<String, Object>> documentsList = new ArrayList<>();

        CollectionReference collection = db.collection(collectionName);
        Query query = collection.orderBy(orderByField, Query.Direction.DESCENDING);

        // Execute the query
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documentsList.add(document.getData());
        }
        return documentsList;
    }

    public void updateData(String collectionName, String documentId, Map<String, Object> updatedData)
            throws ExecutionException, InterruptedException {

        // Reference to the Firestore collection
        CollectionReference collection = db.collection(collectionName);

        // Reference to the document to update
        DocumentReference docRef = collection.document(documentId);

        // Update the document
        ApiFuture<WriteResult> future = docRef.set(updatedData, SetOptions.merge());

        // Wait for the update to complete
        future.get();
    }

}
