package com.Tables;

import javax.persistence.*;

/**
 * Created by pavel on 17.07.17.
 */
@Entity
@Table(name = "BaseMessage", schema = "StorageMessage", catalog = "")
public class BaseMessageEntity {
    private int idBaseMessage;
    private Integer recepientId;
    private Integer senderId;
    private int messageId;
    private byte delivery;
    private byte read;
    private SystemEntity systemByRecepientId;
    private SystemEntity systemBySenderId;
    private MessageEntity messageByMessageId;

    @Id
    @Column(name = "idBaseMessage", nullable = false)
    public int getIdBaseMessage() {
        return idBaseMessage;
    }

    public void setIdBaseMessage(int idBaseMessage) {
        this.idBaseMessage = idBaseMessage;
    }

    @Basic
    @Column(name = "Recepient_Id", nullable = true)
    public Integer getRecepientId() {
        return recepientId;
    }

    public void setRecepientId(Integer recepientId) {
        this.recepientId = recepientId;
    }

    @Basic
    @Column(name = "Sender_Id", nullable = true)
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "Message_Id", nullable = false)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "Delivery", nullable = false)
    public byte getDelivery() {
        return delivery;
    }

    public void setDelivery(byte delivery) {
        this.delivery = delivery;
    }

    @Basic
    @Column(name = "Read", nullable = false)
    public byte getRead() {
        return read;
    }

    public void setRead(byte read) {
        this.read = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseMessageEntity that = (BaseMessageEntity) o;

        if (idBaseMessage != that.idBaseMessage) return false;
        if (messageId != that.messageId) return false;
        if (delivery != that.delivery) return false;
        if (read != that.read) return false;
        if (recepientId != null ? !recepientId.equals(that.recepientId) : that.recepientId != null) return false;
        if (senderId != null ? !senderId.equals(that.senderId) : that.senderId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBaseMessage;
        result = 31 * result + (recepientId != null ? recepientId.hashCode() : 0);
        result = 31 * result + (senderId != null ? senderId.hashCode() : 0);
        result = 31 * result + messageId;
        result = 31 * result + (int) delivery;
        result = 31 * result + (int) read;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Recepient_Id", referencedColumnName = "idSystem")
    public SystemEntity getSystemByRecepientId() {
        return systemByRecepientId;
    }

    public void setSystemByRecepientId(SystemEntity systemByRecepientId) {
        this.systemByRecepientId = systemByRecepientId;
    }

    @ManyToOne
    @JoinColumn(name = "Sender_Id", referencedColumnName = "idSystem")
    public SystemEntity getSystemBySenderId() {
        return systemBySenderId;
    }

    public void setSystemBySenderId(SystemEntity systemBySenderId) {
        this.systemBySenderId = systemBySenderId;
    }

    @ManyToOne
    @JoinColumn(name = "Message_Id", referencedColumnName = "IdMessage", nullable = false)
    public MessageEntity getMessageByMessageId() {
        return messageByMessageId;
    }

    public void setMessageByMessageId(MessageEntity messageByMessageId) {
        this.messageByMessageId = messageByMessageId;
    }
}
