package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class ActionStop implements IAction {

    private String description;
    private Type type;
    private ActionType actionType;
    private String icon;

    public ActionStop() {
        description = "Don't move";
        type = Type.IN_TIME;
        actionType = ActionType.STOP;
        icon = "";
    }
}
