package com.example.appmp3.model.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.appmp3.model.entity.Category;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class CategoryRepository {
    private static final String COLLECTION_CATEGORIES = "collections";
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Inject
    public CategoryRepository() {
    }

    public Observable<List<Category>> getCategories() {
        return Observable.create(emitter ->
                firebaseFirestore
                        .collection(COLLECTION_CATEGORIES)
                        .get()
                        .addOnSuccessListener(documentSnapshot -> {
                            List<Category> categories = documentSnapshot.toObjects(Category.class);
                            emitter.onNext(categories);
                        })
                        .addOnFailureListener(emitter::onError));
    }
}
