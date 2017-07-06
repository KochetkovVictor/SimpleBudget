package ru.simplebudget.model.common;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "shopnet")
@StaticMetamodel(ShopNet.class)
public class ShopNet {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopNet() {

    }

    public ShopNet(Long id) {
        this.id = id;
    }

    @Id
    private
    Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopNet shopNet = (ShopNet) o;

        return id.equals(shopNet.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
