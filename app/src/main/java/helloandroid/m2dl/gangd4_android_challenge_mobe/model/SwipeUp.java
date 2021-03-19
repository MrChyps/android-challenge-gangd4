package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.view.MotionEvent;
import android.view.View;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class SwipeUp extends TouchScreen {

    public SwipeUp(GameActivity gameActivity) {
        super(gameActivity,"Swipe Up", ActionType.SWIPE_UP, "icon");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public void onSwipeUp() {
        setChanged();
        this.notifyObservers(true);
    }
}
