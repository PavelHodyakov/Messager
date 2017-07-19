package com.Tables;

import javax.persistence.*;

/**
 * Created by pavel on 17.07.17.
 */
@Entity
@Table(name = "Subscription", schema = "public", catalog = "Messager")
public class SubscriptionEntity {
    private int idSubscribe;
    private int systemId;
    private String address;
    private SystemEntity systemBySystemId;

    @Id
    @Column(name = "Id_subscribe")
    public int getIdSubscribe() {
        return idSubscribe;
    }

    public void setIdSubscribe(int idSubscribe) {
        this.idSubscribe = idSubscribe;
    }

    @Basic
    @Column(name = "system_id")
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionEntity that = (SubscriptionEntity) o;

        if (idSubscribe != that.idSubscribe) return false;
        if (systemId != that.systemId) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSubscribe;
        result = 31 * result + systemId;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "system_id", referencedColumnName = "Id_system", nullable = false)
    public SystemEntity getSystemBySystemId() {
        return systemBySystemId;
    }

    public void setSystemBySystemId(SystemEntity systemBySystemId) {
        this.systemBySystemId = systemBySystemId;
    }
}
