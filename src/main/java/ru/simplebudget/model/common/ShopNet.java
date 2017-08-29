package ru.simplebudget.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "shopnet")
@JsonRootName("suggestions")
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
    @JsonProperty("data")
    private
    Long id;
    @JsonProperty("value")
    private String name;
}
