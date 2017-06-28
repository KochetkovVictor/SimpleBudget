package ru.simplebudget.model.out;

import ru.simplebudget.model.common.Purse;
import ru.simplebudget.model.common.Shop;
import ru.simplebudget.model.user.User;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;


@NamedQueries({
        @NamedQuery(name= Receipt.GET_BETWEEN_DATETIME,
                query = "SELECT r FROM Receipt r WHERE r.user.id = :userId AND r.receiptDate BETWEEN :startDateTime " +
                        "AND :endDateTime ORDER BY r.receiptDate DESC "),
        @NamedQuery(name="Receipt.join.ShopNet.BetweenDateTime", query = "SELECT r FROM Receipt r JOIN r.shop.netName " +
                "WHERE r.user.id = :userId AND r.shop.netName.id=:shopNetId AND r.receiptDate BETWEEN :startDateTime AND :endDateTime ORDER BY r.receiptDate DESC ")

})


@Entity
@Table(name = "receipt")
@StaticMetamodel(Receipt.class)
public class Receipt {

    public static  final String GET_BETWEEN_DATETIME = "Receipt.getBetweenDateTime";
    public static  final String JOIN_SHOPNET_GET_BETWEEN_DATETIME = "Receipt.join.ShopNet.BetweenDateTime";
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name="shopid")
    private
    Shop shop;

    @Column(name= "active")
    private
    boolean active;

    @JoinColumn(name="purseId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private
    Purse purse;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private
    User user;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

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
/* public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/
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

    public Receipt(Long id){this.id=id;}
    public Receipt() {
        this.receiptDate =LocalDateTime.now().toLocalDate();
        this.active=true;
    }

    public Receipt(Long id, Shop shop, LocalDate receiptDate, Double amount,Purse purse, boolean active)
    {
        this.id=id;
        this.active=active;
        this.receiptDate=receiptDate;
        this.amount=amount;
        this.shop=shop;
        this.purse=purse;
    }
    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", amount=" + amount +
                ", receiptDate=" + receiptDate +
                ", shop=" + shop +
                ", active=" + active +
                ", purse=" +purse+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        return id.equals(receipt.id);
    }

    @Override
    public int hashCode() {
        return id==null ? 0:id.hashCode();
    }
}
