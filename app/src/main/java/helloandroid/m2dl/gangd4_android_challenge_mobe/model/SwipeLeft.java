package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.view.MotionEvent;
import android.view.View;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class SwipeLeft extends TouchScreen {

    public SwipeLeft(GameActivity gameActivity) {
        super(gameActivity,"Swipe Left", ActionType.SWIPE_LEFT, "icon");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
