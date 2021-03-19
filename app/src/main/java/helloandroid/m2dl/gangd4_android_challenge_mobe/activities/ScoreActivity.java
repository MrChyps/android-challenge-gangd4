package helloandroid.m2dl.gangd4_android_challenge_mobe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import helloandroid.m2dl.gangd4_android_challenge_mobe.R;
import helloandroid.m2dl.gangd4_android_challenge_mobe.adapter.ScoreListAdapter;
import helloandroid.m2dl.gangd4_android_challenge_mobe.services.ScoreService;

public class ScoreActivity extends AppCompatActivity {

    private ScoreService scoreService;
    private GridView scoreGridView;
    private ScoreListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_score);
        scoreService = ScoreService.getInstance(this);
        scoreGridView = findViewById(R.id.scoreGridView);
        refreshAdapterList();
    }

    private void refreshAdapterList() {
        myAdapter = new ScoreListAdapter(this, scoreService.getScores());
        scoreGridView.setAdapter(myAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAdapterList();
    }

    public void backToMainActivity(View v){
        onBackPressed();
    }
}