package com.StorageMessages;

import java.util.List;

/**
 * Created by pavel on 13.07.17.
 */
public class MessageImpl implements Message {

    String Caption;
    String Message;
    List<SystemImpl> Addressees;
    SystemImpl Owner;
    OptionsImpl Confirm;
    long id=-2;

    public MessageImpl(String caption, String message, List<SystemImpl> addressees, SystemImpl owner, OptionsImpl confirm) {
        Caption = caption;
        Message = message;
        Addressees = addressees;
        Owner = owner;
        Confirm = confirm;
    }

    public OptionsImpl getConfirm() {
        return Confirm;
    }

    public List<SystemImpl> getAddressees() {
        return Addressees;
    }

    public void setId(long id) {
        this.id = id;
    }
}
