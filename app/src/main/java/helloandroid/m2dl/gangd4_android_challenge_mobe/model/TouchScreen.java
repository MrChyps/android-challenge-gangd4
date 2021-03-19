package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public abstract class TouchScreen implements IAction {

    private String description;
    private Type type;

    public TouchScreen(String description) {
        this.description = description;
        type = Type.INSTANT;
    }

}
