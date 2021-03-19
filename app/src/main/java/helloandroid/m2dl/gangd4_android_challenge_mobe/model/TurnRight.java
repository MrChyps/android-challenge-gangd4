package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class TurnRight extends Orientation {

    private ActionType actionType;
    private String icon;

    public TurnRight() {
        super("Turn tablet right");
        actionType = ActionType.TURN_RIGHT;
        icon = "";
    }
}
