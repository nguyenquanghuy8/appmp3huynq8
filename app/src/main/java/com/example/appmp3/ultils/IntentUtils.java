package com.example.appmp3.ultils;

import android.content.Intent;

public class IntentUtils {

    public static void bindIntentAudio(Intent intent) {
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/mp3");
    }

    public static void bindIntentVideo(Intent intent) {
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
    }
}
