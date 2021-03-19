package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.view.MotionEvent;
import android.view.View;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class SwipeRight extends TouchScreen {
    public SwipeRight(GameActivity gameActivity) {
        super(gameActivity,"Swipe Right", ActionType.SWIPE_RIGHT, "icon");

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public void onSwipeRight() {
        setChanged();
        this.notifyObservers(true);
    }
}
