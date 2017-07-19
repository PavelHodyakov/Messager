package com.Tables;

import javax.persistence.*;

/**
 * Created by pavel on 19.07.17.
 */
@Entity
@Table(name = "basemessage", schema = "public", catalog = "Messager")
public class BasemessageEntity {
    private Boolean delivery;
    private Integer messageId;
    private Boolean reading;
    private Integer recipientId;
    private Integer senderId;
    private int idBase;
    private MessagesEntity messagesByMessageId;
    private SystemEntity systemByRecipientId;
    private SystemEntity systemBySenderId;

    public BasemessageEntity(int idBase,Integer messageId,Integer recipientId, Integer senderId, Boolean delivery, Boolean reading) {
        this.delivery = delivery;
        this.messageId = messageId;
        this.reading = reading;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.idBase = idBase;
    }

    public BasemessageEntity() {
    }

    @Basic
    @Column(name = "delivery")
    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    @Basic
    @Column(name = "message_id", updatable = false, insertable = false)
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "reading")
    public Boolean getReading() {
        return reading;
    }

    public void setReading(Boolean reading) {
        this.reading = reading;
    }

    @Basic
    @Column(name = "recipient_id", updatable = false, insertable = false)
    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    @Basic
    @Column(name = "sender_id", updatable = false, insertable = false)
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Id
    @Column(name = "id_base")
    public int getIdBase() {
        return idBase;
    }

    public void setIdBase(int idBase) {
        this.idBase = idBase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasemessageEntity that = (BasemessageEntity) o;

        if (idBase != that.idBase) return false;
        if (delivery != null ? !delivery.equals(that.delivery) : that.delivery != null) return false;
        if (messageId != null ? !messageId.equals(that.messageId) : that.messageId != null) return false;
        if (reading != null ? !reading.equals(that.reading) : that.reading != null) return false;
        if (recipientId != null ? !recipientId.equals(that.recipientId) : that.recipientId != null) return false;
        if (senderId != null ? !senderId.equals(that.senderId) : that.senderId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = delivery != null ? delivery.hashCode() : 0;
        result = 31 * result + (messageId != null ? messageId.hashCode() : 0);
        result = 31 * result + (reading != null ? reading.hashCode() : 0);
        result = 31 * result + (recipientId != null ? recipientId.hashCode() : 0);
        result = 31 * result + (senderId != null ? senderId.hashCode() : 0);
        result = 31 * result + idBase;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id_message")
    public MessagesEntity getMessagesByMessageId() {
        return messagesByMessageId;
    }

    public void setMessagesByMessageId(MessagesEntity messagesByMessageId) {
        this.messagesByMessageId = messagesByMessageId;
    }

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "id_system")
    public SystemEntity getSystemByRecipientId() {
        return systemByRecipientId;
    }

    public void setSystemByRecipientId(SystemEntity systemByRecipientId) {
        this.systemByRecipientId = systemByRecipientId;
    }

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id_system")
    public SystemEntity getSystemBySenderId() {
        return systemBySenderId;
    }

    public void setSystemBySenderId(SystemEntity systemBySenderId) {
        this.systemBySenderId = systemBySenderId;
    }
}
