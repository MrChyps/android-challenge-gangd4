package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.view.MotionEvent;
import android.view.View;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class SwipeDown extends TouchScreen{

    public SwipeDown(GameActivity gameActivity) {
        super(gameActivity,"Swipe Down", ActionType.SWIPE_DOWN, "icon");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public void onSwipeDown() {
        setChanged();
        this.notifyObservers(true);
    }
}
