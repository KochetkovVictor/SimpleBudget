package ru.simplebudget.model.out;

import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Shop;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NamedQueries({
        @NamedQuery(name= Receipt.GET_BETWEEN_DATETIME,
                query = "SELECT r FROM Receipt r WHERE r.receiptDate BETWEEN :startDateTime " +
                        "AND :endDateTime ORDER BY r.receiptDate DESC ")
})


@Entity
@Table(name = "receipt")
@StaticMetamodel(Receipt.class)
public class Receipt {

    public static  final String GET_BETWEEN_DATETIME = "Receipt.getBetweenDateTime";

    @Id
    @SequenceGenerator(name = "global_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private
    Long id;
    @Column(name = "amount")
    private
    Double amount;
    @Column(name = "datetime", columnDefinition = "timestamp default now()")
    private
    LocalDate receiptDate;
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
        this.receiptDate =LocalDateTime.now().toLocalDate();
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }


    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", amount=" + amount +
                ", receiptDate=" + receiptDate +
                ", shop=" + shop +
                ", active=" + active +
                '}';
    }
}
