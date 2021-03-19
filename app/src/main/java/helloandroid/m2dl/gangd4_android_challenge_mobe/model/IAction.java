package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import android.graphics.drawable.Icon;

import java.util.Observable;

import helloandroid.m2dl.gangd4_android_challenge_mobe.activities.GameActivity;

public abstract class IAction extends Observable {
    protected GameActivity gameActivity;
    protected String description;
    protected ActionType actionType;
    protected Type type;
    protected String icon;

    public IAction(GameActivity gameActivity, String description, ActionType actionType, Type type, String icon) {
        this.gameActivity = gameActivity;
        this.description = description;
        this.actionType = actionType;
        this.type = type;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Type getType() {
        return type;
    }

    public String getIcon() {
        return icon;
    }
}
