package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class Touch extends TouchScreen {

    private ActionType actionType;
    private String icon;

    public Touch() {
        super("Tap the screen ");
        actionType = ActionType.TOUCH;
        icon = "";
    }
}
