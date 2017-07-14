package com.StorageMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavel on 13.07.17.
 */
public interface Message {
    public OptionsImpl getConfirm();

    public List<SystemImpl> getAddressees();

}
