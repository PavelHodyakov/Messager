package com.messager;

/**
 * Created by pavel on 13.07.17.
 */
public class SystemImpl implements System {

    String name;

    public SystemImpl(String string) {
        name=string;
    }

    public String getLogin() {
        return name;
    }
}
