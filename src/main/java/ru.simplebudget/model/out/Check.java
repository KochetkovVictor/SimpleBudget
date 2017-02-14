package ru.simplebudget.model.out;




import ru.simplebudget.model.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Noutbook on 23.01.2017.
 */
@Entity
@Table(name="Check")

public class Check {

    @Id
    Long checkId;
    @Column(name="Amount")
    Long amount;
    @Column(name="DateTime", columnDefinition = "timestamp default now()")
    LocalDateTime dateTime;

    @Column(name="Products")
    ArrayList<Product> products;

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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
