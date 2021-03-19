package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;
import helloandroid.m2dl.gangd4_android_challenge_mobe.services.ScoreService;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScoreService.initDatabase(this);
        this.context = this;

        startActivity(new Intent(context, ScoreActivity.class));
    }
}