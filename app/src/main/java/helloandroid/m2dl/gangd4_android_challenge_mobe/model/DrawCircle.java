package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class DrawCircle extends TouchScreen {

    private ActionType actionType;
    private String icon;

    public DrawCircle() {
        super("Draw a Circle");
        actionType = ActionType.DRAW_CIRCLE;
        icon = "";
    }
}
