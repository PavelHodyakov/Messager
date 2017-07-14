package com.StorageMessages;

/**
 * Created by pavel on 13.07.17.
 */
public class OptionsImpl implements Options {

    boolean ConfirmDelivery;//Требование для сообщения:подтверждение доставки
    boolean ConfirmReading;//Требование для сообщения:подтверждение прочтения
    boolean DeliveryConfirm=false;//была ли подтверждена доставка после отправки
    boolean ReadingConfirm=false;//было ли подтверждено прочтение после отправки


    public OptionsImpl(boolean confirmDelivery, boolean confirmReading) {
        ConfirmDelivery = confirmDelivery;
        ConfirmReading = confirmReading;
        /*подумать надо ли выставлять остальные показатели*/
    }

    public OptionsImpl() {
    }

    public void setConfirmDelivery(boolean confirmDelivery) {
        ConfirmDelivery = confirmDelivery;
    }

    public void setConfirmReading(boolean confirmReading) {
        ConfirmReading = confirmReading;
    }

    public void setDeliveryConfirm(boolean deliveryConfirm) {
        DeliveryConfirm = deliveryConfirm;
    }

    public void setReadingConfirm(boolean readingConfirm) {
        ReadingConfirm = readingConfirm;
    }

    public boolean isConfirmDelivery() {
        return ConfirmDelivery;
    }

    public boolean isConfirmReading() {
        return ConfirmReading;
    }

    public boolean isDeliveryConfirm() {
        return DeliveryConfirm;
    }

    public boolean isReadingConfirm() {
        return ReadingConfirm;
    }
}
