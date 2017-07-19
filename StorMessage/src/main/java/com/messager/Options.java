package com.messager;

/**
 * Created by pavel on 13.07.17.
 * класс для гарантирования доставки и гарантирования сообщения
 */
public interface Options {

    public boolean isConfirmDelivery();

    public boolean isConfirmReading();

    public boolean isDeliveryConfirm();

    public boolean isReadingConfirm();

    public void setConfirmDelivery(boolean confirmDelivery);

    public void setConfirmReading(boolean confirmReading);

    public void setDeliveryConfirm(boolean deliveryConfirm);

    public void setReadingConfirm(boolean readingConfirm);
}
