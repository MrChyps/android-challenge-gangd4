package helloandroid.m2dl.gangd4_android_challenge_mobe.services;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.BuildConfig;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import helloandroid.m2dl.gangd4_android_challenge_mobe.model.Score;

public class ScoreService {
    public static final String DB_URL = "https://mobe-groupe4-f96c1-default-rtdb.europe-west1.firebasedatabase.app/";
    public static final String SCORES_URL = "challenge_scores";

    private final DatabaseReference mDatabase;
    private final List<Score> loadedScores;
    private static ScoreService INSTANCE;

    /**
     * Initialize the database
     * @param context application context
     */
    public static void initDatabase(Context context) {
        ScoreService.INSTANCE = new ScoreService(context);
    }

    /**
     * Get the singleton instance of the score service
     * @param context application context
     * @return instance of the score service
     */
    public static ScoreService getInstance(Context context) {
        if (ScoreService.INSTANCE == null)
            initDatabase(context);
        return ScoreService.INSTANCE;
    }

    private ScoreService(Context context) {
        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseOptions options = new FirebaseOptions.Builder().setApplicationId(BuildConfig.APPLICATION_ID).setDatabaseUrl(DB_URL).build();
            FirebaseApp.initializeApp(context, options);
        }
        mDatabase = FirebaseDatabase.getInstance(ScoreService.DB_URL).getReference();
        this.loadedScores = new ArrayList<>();
        this.loadScores();
    }

    private void addScore(Score score) {
        if (!loadedScores.contains(score)) {
            if (loadedScores.size() < 10 || loadedScores.get(9).getScore() < score.getScore()) {
                loadedScores.add(score);
                Collections.sort(loadedScores);
                if (loadedScores.size() > 10)
                    loadedScores.remove(10);
            }
        }
    }

    public void loadScores() {
        this.mDatabase.child(ScoreService.SCORES_URL).orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Score score = snapshot.getValue(Score.class);
                System.out.println(score);
                addScore(score);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Score score = snapshot.getValue(Score.class);
                if(loadedScores.contains(score)) {
                    loadedScores.remove(score);
                    addScore(score);
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Score score = snapshot.getValue(Score.class);
                loadedScores.remove(score);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * Returns the top 10 of the loaded scores
     * @return scores
     */
    public List<Score> getScores() {
        return this.loadedScores;
    }

    /**
     * Save the current score locally and on database
     * @param score score to save
     */
    public void saveScore(Score score) {
        this.mDatabase.child(ScoreService.SCORES_URL).child(score.getId()).setValue(score)
                .addOnSuccessListener(aVoid ->
                        Log.i("DATABASE", "Set new high score with ID " + score.getId() + ".")
                )
                .addOnFailureListener(e ->
                        Log.e("DATABASE", "Fail to set new high score with ID" + score.getId() + ".")
                );
    }
}
