package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class Shake extends IAction {

    public Shake(GameActivity gameActivity) {
        super(gameActivity, "Shake the tablet", ActionType.SHAKE, Type.INSTANT, "icon");
    }
}
