package ru.simplebudget.model;

import javax.persistence.*;

@Entity

@Table(name = "shops")
public class Shop {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_id_seq")
    Long shopId;
    @Column(name="adress")
    String adress;
    @Column (name = "name")
    String name;

    @OneToOne
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
