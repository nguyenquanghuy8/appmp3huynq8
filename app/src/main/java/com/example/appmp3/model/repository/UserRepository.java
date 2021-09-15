package com.example.appmp3.model.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private FirebaseAuth firebaseAuth;

    @Inject
    public UserRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void registerUser(String email, String password, RegisterCallback registerCallback) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnFailureListener(e -> {
                    registerCallback.onFail(e.getMessage());
                })
                .addOnSuccessListener(authResult -> {
                    registerCallback.onSuccess();
                });
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

    public interface RegisterCallback {
        void onSuccess();

        void onFail(String error);
    }

    public interface LoginCallback {
        void onSuccess();

        void onFail(String error);
    }
}
