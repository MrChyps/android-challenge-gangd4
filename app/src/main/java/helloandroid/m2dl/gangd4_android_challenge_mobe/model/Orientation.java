package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

public abstract class Orientation implements IAction {

    private String description;
    private Type type;

    public Orientation (String description) {
        this.description = description;
        type = Type.INSTANT;
    }
}
