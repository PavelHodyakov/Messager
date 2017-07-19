package com.Tables;

import javax.persistence.*;

/**
 * Created by pavel on 17.07.17.
 */
@Entity
@Table(name = "BaseMessage", schema = "public", catalog = "Messager")
public class BaseMessageEntity {
    private long idBase;
    private long recipientId;
    private long senderId;
    private long messageId;
    private boolean delivery;
    private boolean reading;
    private SystemEntity systemByRecipientId;
    private SystemEntity systemBySenderId;
    private MessagesEntity messagesByMessageId;

    @Id
    @Column(name = "id_base")
    public long getIdBase() {
        return idBase;
    }

    public void setIdBase(long idBase) {
        this.idBase = idBase;
    }

    @Basic
    @Column(name = "recipient_id")
    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    @Basic
    @Column(name = "sender_id")
    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "message_id")
    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "delivery")
    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    @Basic
    @Column(name = "reading")
    public boolean isReading() {
        return reading;
    }

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseMessageEntity that = (BaseMessageEntity) o;

        if (idBase != that.idBase) return false;
        if (recipientId != that.recipientId) return false;
        if (senderId != that.senderId) return false;
        if (messageId != that.messageId) return false;
        if (delivery != that.delivery) return false;
        if (reading != that.reading) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idBase ^ (idBase >>> 32));
        result = 31 * result + (int) (recipientId ^ (recipientId >>> 32));
        result = 31 * result + (int) (senderId ^ (senderId >>> 32));
        result = 31 * result + (int) (messageId ^ (messageId >>> 32));
        result = 31 * result + (delivery ? 1 : 0);
        result = 31 * result + (reading ? 1 : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "Id_system", nullable = false)
    public SystemEntity getSystemByRecipientId() {
        return systemByRecipientId;
    }

    public void setSystemByRecipientId(SystemEntity systemByRecipientId) {
        this.systemByRecipientId = systemByRecipientId;
    }

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "Id_system", nullable = false)
    public SystemEntity getSystemBySenderId() {
        return systemBySenderId;
    }

    public void setSystemBySenderId(SystemEntity systemBySenderId) {
        this.systemBySenderId = systemBySenderId;
    }

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id_message", nullable = false)
    public MessagesEntity getMessagesByMessageId() {
        return messagesByMessageId;
    }

    public void setMessagesByMessageId(MessagesEntity messagesByMessageId) {
        this.messagesByMessageId = messagesByMessageId;
    }
}
