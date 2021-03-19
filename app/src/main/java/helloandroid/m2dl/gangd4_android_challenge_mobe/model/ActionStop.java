package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public class ActionStop extends IAction {

    private String description;
    private Type type;
    private ActionType actionType;
    private String icon;

    public ActionStop(GameActivity gameActivity) {
        super(gameActivity, "Don't move", ActionType.STOP, Type.IN_TIME, "icon");
    }
}
