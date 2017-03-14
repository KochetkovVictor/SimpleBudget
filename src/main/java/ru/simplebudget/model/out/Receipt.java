package ru.simplebudget.model.out;

import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Shop;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;


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
    @SequenceGenerator(name = "global_seq")
    @Column(name = "id")
    private
    Long id;
    @Column(name = "amount")
    private
    Long amount;
    @Column(name = "datetime", columnDefinition = "timestamp default now()")
    private
    LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name="shopid")
    private
    Shop shop;
    @Column(name= "active")
    private
    boolean active;

    @JoinColumn(name="purseId")
    @ManyToOne
    private
    Purse purse;
    public Purse getPurse() {
        return purse;
    }

    public void setPurse(Purse purse) {
        this.purse = purse;
    }


    /*@Column(name="product")
    @OneToMany(mappedBy = "id", fetch=FetchType.EAGER)
    @JoinColumn(name="product")
    private List<Product> products;*/

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


    public Receipt() {
    }

   /* public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateTime=" + dateTime +
                ", shop=" + shop +
                ", active=" + active +
                '}';
    }
}
