package ru.simplebudget.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "shop")
@StaticMetamodel(Shop.class)
@XmlRootElement
public class Shop {

    @Id
    @SequenceGenerator(name = "shops_id_seq", sequenceName = "shops_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shops_id_seq")
    @Column(name = "id")
    private
    Long id;
    @Column(name="adress")
    private
    String adress;
    @Column (name = "name")
    private
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopnetid")
    @JsonIgnore
    private
    ShopNet netName;

    public Shop() {
    }
    public Shop(Long id){
        this.id =id;
    }
    @XmlElement
    public Long getId() {
        return id;
    }

    public void setId(Long shopId) {
        this.id = shopId;
    }
    @XmlElement
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopNet getNetName() {
        return netName;
    }

    public void setNetName(ShopNet netName) {
        this.netName = netName;
    }
}
