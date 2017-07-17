package com.Tables;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by pavel on 17.07.17.
 */
@Entity
@Table(name = "Message", schema = "StorageMessage", catalog = "")
public class MessageEntity {
    private int idMessage;
    private String caption;
    private String content;
    private byte delRequire;
    private byte readRequire;
    private Collection<BaseMessageEntity> baseMessagesByIdMessage;

    @Id
    @Column(name = "IdMessage", nullable = false)
    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    @Basic
    @Column(name = "Caption", nullable = true, length = -1)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Basic
    @Column(name = "Content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "Del_Require", nullable = false)
    public byte getDelRequire() {
        return delRequire;
    }

    public void setDelRequire(byte delRequire) {
        this.delRequire = delRequire;
    }

    @Basic
    @Column(name = "Read_require", nullable = false)
    public byte getReadRequire() {
        return readRequire;
    }

    public void setReadRequire(byte readRequire) {
        this.readRequire = readRequire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (idMessage != that.idMessage) return false;
        if (delRequire != that.delRequire) return false;
        if (readRequire != that.readRequire) return false;
        if (caption != null ? !caption.equals(that.caption) : that.caption != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMessage;
        result = 31 * result + (caption != null ? caption.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (int) delRequire;
        result = 31 * result + (int) readRequire;
        return result;
    }

    @OneToMany(mappedBy = "messageByMessageId")
    public Collection<BaseMessageEntity> getBaseMessagesByIdMessage() {
        return baseMessagesByIdMessage;
    }

    public void setBaseMessagesByIdMessage(Collection<BaseMessageEntity> baseMessagesByIdMessage) {
        this.baseMessagesByIdMessage = baseMessagesByIdMessage;
    }
}
