package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public class MaskScreen implements IAction {

    private String description;
    private Type type;
    private ActionType actionType;
    private String icon;

    public MaskScreen() {
        description = "Mask the screen";
        type = Type.IN_TIME;
        actionType = ActionType.MASK_SCREEN;
        icon = "";
    }
}
