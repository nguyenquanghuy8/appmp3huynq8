package com.example.appmp3.model.repository;

import com.example.appmp3.model.entity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class UserRepository {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    public static final String COLLECTION_USERS = "users";

    @Inject
    public UserRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public String getCurrentUserUid() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    public Observable<Boolean> registerUser(String email, String password) {
        return Observable.create(emitter ->
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnFailureListener(emitter::onError)
                        .addOnSuccessListener(authResult -> emitter.onNext(true)));
    }

    public Observable<Boolean> storeUser(User user) {
        return Observable.create(emitter ->
                firebaseFirestore
                        .collection(COLLECTION_USERS)
                        .document(getCurrentUserUid())
                        .set(user)
                        .addOnSuccessListener(unused -> emitter.onNext(true))
                        .addOnFailureListener(emitter::onError)
        );
    }

    public Observable<User> getUserInfo() {
        return Observable.create(emitter ->
                firebaseFirestore
                        .collection(COLLECTION_USERS)
                        .document(getCurrentUserUid())
                        .get()
                        .addOnSuccessListener(documentSnapshot -> {
                            User user = documentSnapshot.toObject(User.class);
                            emitter.onNext(user);
                        })
                        .addOnFailureListener(emitter::onError));
    }

    public void loginUser(String email, String password, LoginCallback loginCallback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    loginCallback.onSuccess();
                })
                .addOnFailureListener(e -> {
                    loginCallback.onFail(e.getMessage());
                });
    }

    public interface LoginCallback {
        void onSuccess();

        void onFail(String error);
    }
}
