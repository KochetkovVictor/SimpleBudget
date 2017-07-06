package ru.simplebudget.model.common;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
@Table(name = "shop")
@StaticMetamodel(Shop.class)
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
    private
    ShopNet netName;

    public Shop() {
    }
    public Shop(Long id){
        this.id =id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long shopId) {
        this.id = shopId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        return id.equals(shop.id);
    }

    @Override
    public int hashCode() {
        return id==null ? 0:id.hashCode();
    }
}
