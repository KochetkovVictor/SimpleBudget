package ru.simplebudget.model.out;


import ru.simplebudget.model.Product;
import ru.simplebudget.model.Shop;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name= Receipt.GET_BETWEEN_DATETIME,
                query = "SELECT r FROM Receipt r WHERE r.dateTime BETWEEN :startDateTime " +
                        "AND :endDateTime ORDER BY r.dateTime DESC ")
})


@Entity
@Table(name = "receipt")
@StaticMetamodel(Receipt.class)
public class Receipt {

    public static  final String GET_BETWEEN_DATETIME = "Receipt.getBetweenDateTime";

    @Id
    @SequenceGenerator(name = "receipt_id_seq", sequenceName = "receipt_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_id_seq")
    @Column(name = "id")
    private
    Long checkId;
    @Column(name = "amount")
    private
    Long amount;
    @Column(name = "datetime", columnDefinition = "timestamp default now()")
    private
    LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name="shop_id")
    private
    Shop shop;
    @Column(name= "active")
    private
    boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }


   /* @OneToMany
    @Column(name = "products")
    List<Product> products;*/


    public Receipt() {
    }


    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /*public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/

    @Override
    public String toString() {
        return "Receipt{" +
                "checkId=" + checkId +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", shop=" + shop +
                ", active=" + active +
                '}';
    }
}
