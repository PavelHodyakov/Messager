package com.Tables;

import javax.persistence.*;

/**
 * Created by pavel on 19.07.17.
 */
@Entity
@Table(name = "subscription", schema = "public", catalog = "Messager")
public class SubscriptionEntity {
    private String address;
    private int systemId;
    private int idSub;
    private SystemEntity systemBySystemId;

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "system_id",nullable = false)
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    @Id
    @Column(name = "id_sub")
    public int getIdSub() {
        return idSub;
    }

    public void setIdSub(int idSub) {
        this.idSub = idSub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionEntity that = (SubscriptionEntity) o;

        if (systemId != that.systemId) return false;
        if (idSub != that.idSub) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + systemId;
        result = 31 * result + idSub;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "system_id", referencedColumnName = "id_system", nullable = false, insertable = false, updatable = false)
    public SystemEntity getSystemBySystemId() {
        return systemBySystemId;
    }

    public void setSystemBySystemId(SystemEntity systemBySystemId) {
        this.systemBySystemId = systemBySystemId;
    }
}
