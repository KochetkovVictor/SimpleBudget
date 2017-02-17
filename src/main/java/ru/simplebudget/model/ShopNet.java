package ru.simplebudget.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopnets")
public class ShopNet {
    @Id
    Long id;
    String name;
}
