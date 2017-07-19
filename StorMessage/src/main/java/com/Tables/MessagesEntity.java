package com.Tables;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by pavel on 19.07.17.
 */
@Entity
@Table(name = "messages", schema = "public", catalog = "Messager")
public class MessagesEntity {
    private String caption;
    private String content;
    private Boolean delRequire;
    private Boolean readRequire;
    private int idMessage;
    private Collection<BasemessageEntity> basemessagesByIdMessage;

    @Basic
    @Column(name = "caption")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "del_require")
    public Boolean getDelRequire() {
        return delRequire;
    }

    public void setDelRequire(Boolean delRequire) {
        this.delRequire = delRequire;
    }

    @Basic
    @Column(name = "read_require")
    public Boolean getReadRequire() {
        return readRequire;
    }

    public void setReadRequire(Boolean readRequire) {
        this.readRequire = readRequire;
    }

    @Id
    @Column(name = "id_message")
    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesEntity that = (MessagesEntity) o;

        if (idMessage != that.idMessage) return false;
        if (caption != null ? !caption.equals(that.caption) : that.caption != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (delRequire != null ? !delRequire.equals(that.delRequire) : that.delRequire != null) return false;
        if (readRequire != null ? !readRequire.equals(that.readRequire) : that.readRequire != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = caption != null ? caption.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (delRequire != null ? delRequire.hashCode() : 0);
        result = 31 * result + (readRequire != null ? readRequire.hashCode() : 0);
        result = 31 * result + idMessage;
        return result;
    }

    @OneToMany(mappedBy = "messagesByMessageId")
    public Collection<BasemessageEntity> getBasemessagesByIdMessage() {
        return basemessagesByIdMessage;
    }

    public void setBasemessagesByIdMessage(Collection<BasemessageEntity> basemessagesByIdMessage) {
        this.basemessagesByIdMessage = basemessagesByIdMessage;
    }

    public MessagesEntity(String caption, String content, Boolean delRequire, Boolean readRequire, int idMessage) {
        this.caption = caption;
        this.content = content;
        this.delRequire = delRequire;
        this.readRequire = readRequire;
        this.idMessage = idMessage;
    }

    public MessagesEntity() {
    }
}
