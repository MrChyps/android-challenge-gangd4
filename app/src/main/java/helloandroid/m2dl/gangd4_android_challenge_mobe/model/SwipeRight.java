package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.view.MotionEvent;
import android.view.View;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class SwipeRight extends TouchScreen {

    private ActionType actionType;
    private String icon;

    public SwipeRight(GameActivity gameActivity) {
        super(gameActivity,"Swipe Right", ActionType.SWIPE_RIGHT, "icon");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
