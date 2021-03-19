package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.view.View;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public abstract class TouchScreen extends IAction implements View.OnTouchListener {

    public static final int MIN_MOVE = 30;
    protected Point pointTouchDown;
    protected Point pointTouchUp;

    public TouchScreen(GameActivity gameActivity, String description, ActionType actionType, String icon) {
        super(gameActivity, description, actionType, Type.INSTANT, icon);
    }

}
