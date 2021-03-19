package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class MaskScreen extends IAction {

    public MaskScreen(GameActivity gameActivity) {
        super(gameActivity, "Mask the screen", ActionType.MASK_SCREEN, Type.IN_TIME, "icon");
    }
}
