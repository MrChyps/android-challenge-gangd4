package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class TurnLeft extends Orientation{

    private ActionType actionType;
    private String icon;

    public TurnLeft() {
        super("Turn tablet left");
        actionType = ActionType.TURN_LEFT;
        icon = "";
    }
}
