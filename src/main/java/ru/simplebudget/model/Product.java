package ru.simplebudget.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    Long id;
    String name;
    Long cost;

    public Product() {
    }

    public Product(String name, Long cost) {
        this.name = name;
        this.cost = cost;
    }
}
