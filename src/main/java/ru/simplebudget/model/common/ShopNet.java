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
}
