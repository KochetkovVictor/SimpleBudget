package ru.simplebudget.model.out;


import ru.simplebudget.model.Product;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "check")
public class Check {

    @Id
    @SequenceGenerator(name = "check_id_seq", sequenceName = "check_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_id_seq")
    @Column(name = "id")
    Long checkId;
    @Column(name = "amount")
    Long amount;
    @Column(name = "datetime", columnDefinition = "timestamp default now()")
    LocalDateTime dateTime;
    /*@OneToMany
    @Column(name = "products")
    List<Product> products;*/

    public Check() {
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
}
