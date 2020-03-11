package com.example.library;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FireStoreLiveData<T> extends LiveData<T> {
    private CollectionReference collectionReference;
    private Class aClass;


    public FireStoreLiveData(CollectionReference collectionReference, Class aClass) {
        this.aClass = aClass;
        this.collectionReference = collectionReference;

    }


    EventListener<QuerySnapshot> eventListener = new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshot, @Nullable FirebaseFirestoreException e) {
            if (e != null) {

                return;
            }

            if (queryDocumentSnapshot != null) {
                List<T> aList = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshot.getDocuments()) {
                    T item = (T) documentSnapshot.toObject(aClass);
                    aList.add(item);


                }
                setValue((T) aList);

            }
        }
    };

    @Override
    protected void onActive() {
        super.onActive();

        collectionReference.addSnapshotListener(eventListener);
    }
}
