package com.Tables;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by pavel on 17.07.17.
 */
@Entity
@Table(name = "Messages", schema = "public", catalog = "Messager")
public class MessagesEntity {
    private long idMessage;
    private String content;
    private String caption;
    private boolean delRequire;
    private boolean readRequire;
    private Collection<BaseMessageEntity> baseMessagesByIdMessage;

    @Id
    @Column(name = "id_message")
    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    @Basic
    @Column(name = "Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "Caption")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Basic
    @Column(name = "del_require")
    public boolean isDelRequire() {
        return delRequire;
    }

    public void setDelRequire(boolean delRequire) {
        this.delRequire = delRequire;
    }

    @Basic
    @Column(name = "read_require")
    public boolean isReadRequire() {
        return readRequire;
    }

    public void setReadRequire(boolean readRequire) {
        this.readRequire = readRequire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesEntity that = (MessagesEntity) o;

        if (idMessage != that.idMessage) return false;
        if (delRequire != that.delRequire) return false;
        if (readRequire != that.readRequire) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (caption != null ? !caption.equals(that.caption) : that.caption != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idMessage ^ (idMessage >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (caption != null ? caption.hashCode() : 0);
        result = 31 * result + (delRequire ? 1 : 0);
        result = 31 * result + (readRequire ? 1 : 0);
        return result;
    }

    @OneToMany(mappedBy = "messagesByMessageId")
    public Collection<BaseMessageEntity> getBaseMessagesByIdMessage() {
        return baseMessagesByIdMessage;
    }

    public void setBaseMessagesByIdMessage(Collection<BaseMessageEntity> baseMessagesByIdMessage) {
        this.baseMessagesByIdMessage = baseMessagesByIdMessage;
    }
}
