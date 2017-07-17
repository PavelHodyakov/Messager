package com.Tables;

import javax.persistence.*;

/**
 * Created by pavel on 17.07.17.
 */
@Entity
@Table(name = "Subscription", schema = "StorageMessage", catalog = "")
public class SubscriptionEntity {
    private int idSubscription;
    private Integer systemId;
    private String address;
    private SystemEntity systemBySystemId;

    @Id
    @Column(name = "idSubscription", nullable = false)
    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    @Basic
    @Column(name = "System_id", nullable = true)
    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    @Basic
    @Column(name = "Address", nullable = false, length = 100)
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

        if (idSubscription != that.idSubscription) return false;
        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSubscription;
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "System_id", referencedColumnName = "idSystem")
    public SystemEntity getSystemBySystemId() {
        return systemBySystemId;
    }

    public void setSystemBySystemId(SystemEntity systemBySystemId) {
        this.systemBySystemId = systemBySystemId;
    }
}
