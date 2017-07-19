package com.Tables;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by pavel on 19.07.17.
 */
@Entity
@Table(name = "system", schema = "public", catalog = "Messager")
public class SystemEntity {
    private String name;
    private String password;
    private int idSystem;
    private Collection<BasemessageEntity> basemessagesByIdSystem;
    private Collection<BasemessageEntity> basemessagesByIdSystem_0;
    private Collection<SubscriptionEntity> subscriptionsByIdSystem;

    public SystemEntity(String name, String password, int idSystem) {
        this.name = name;
        this.password = password;
        this.idSystem = idSystem;
    }

    public SystemEntity() {
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @Column(name = "id_system")
    public int getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(int idSystem) {
        this.idSystem = idSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemEntity that = (SystemEntity) o;

        if (idSystem != that.idSystem) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + idSystem;
        return result;
    }

    @OneToMany(mappedBy = "systemByRecipientId")
    public Collection<BasemessageEntity> getBasemessagesByIdSystem() {
        return basemessagesByIdSystem;
    }

    public void setBasemessagesByIdSystem(Collection<BasemessageEntity> basemessagesByIdSystem) {
        this.basemessagesByIdSystem = basemessagesByIdSystem;
    }

    @OneToMany(mappedBy = "systemBySenderId")
    public Collection<BasemessageEntity> getBasemessagesByIdSystem_0() {
        return basemessagesByIdSystem_0;
    }

    public void setBasemessagesByIdSystem_0(Collection<BasemessageEntity> basemessagesByIdSystem_0) {
        this.basemessagesByIdSystem_0 = basemessagesByIdSystem_0;
    }

    @OneToMany(mappedBy = "systemBySystemId")
    public Collection<SubscriptionEntity> getSubscriptionsByIdSystem() {
        return subscriptionsByIdSystem;
    }

    public void setSubscriptionsByIdSystem(Collection<SubscriptionEntity> subscriptionsByIdSystem) {
        this.subscriptionsByIdSystem = subscriptionsByIdSystem;
    }
}
