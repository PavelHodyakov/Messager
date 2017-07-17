package com.Tables;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by pavel on 17.07.17.
 */
@Entity
@Table(name = "System", schema = "StorageMessage", catalog = "")
public class SystemEntity {
    private int idSystem;
    private String name;
    private String password;
    private Collection<BaseMessageEntity> baseMessagesByIdSystem;
    private Collection<BaseMessageEntity> baseMessagesByIdSystem_0;
    private Collection<SubscriptionEntity> subscriptionsByIdSystem;

    @Id
    @Column(name = "idSystem", nullable = false)
    public int getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(int idSystem) {
        this.idSystem = idSystem;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        int result = idSystem;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "systemByRecepientId")
    public Collection<BaseMessageEntity> getBaseMessagesByIdSystem() {
        return baseMessagesByIdSystem;
    }

    public void setBaseMessagesByIdSystem(Collection<BaseMessageEntity> baseMessagesByIdSystem) {
        this.baseMessagesByIdSystem = baseMessagesByIdSystem;
    }

    @OneToMany(mappedBy = "systemBySenderId")
    public Collection<BaseMessageEntity> getBaseMessagesByIdSystem_0() {
        return baseMessagesByIdSystem_0;
    }

    public void setBaseMessagesByIdSystem_0(Collection<BaseMessageEntity> baseMessagesByIdSystem_0) {
        this.baseMessagesByIdSystem_0 = baseMessagesByIdSystem_0;
    }

    @OneToMany(mappedBy = "systemBySystemId")
    public Collection<SubscriptionEntity> getSubscriptionsByIdSystem() {
        return subscriptionsByIdSystem;
    }

    public void setSubscriptionsByIdSystem(Collection<SubscriptionEntity> subscriptionsByIdSystem) {
        this.subscriptionsByIdSystem = subscriptionsByIdSystem;
    }
}
