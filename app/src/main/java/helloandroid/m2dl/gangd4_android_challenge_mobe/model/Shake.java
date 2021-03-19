package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class Shake implements IAction {

    private String description;
    private Type type;
    private ActionType actionType;
    private String icon;

    public Shake() {
        description = "Shake the tablet";
        type = Type.INSTANT;
        actionType = ActionType.SHAKE;
        icon = "";
    }

}
