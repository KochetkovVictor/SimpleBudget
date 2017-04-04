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
    Long shopId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
}
