package ru.simplebudget.model.common;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;


@Entity
@Table(name = "purse")
@StaticMetamodel(Purse.class)
public class Purse {

    @Id
    @SequenceGenerator(name = "global_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private
    Long id;
    @Column(name = "description", unique = true)
    @NotEmpty
    private
    String description;
    @Column(name = "amount", columnDefinition = "default 0.0")
    private
    Double amount;

    @Column(name = "active")
    private
    boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Purse() {
        this.id=null;
        this.amount=0.0;
    }

    public Purse(Long id) {
        this.id = id;
    }

    public Purse(Long id, String description, Double amount, boolean active) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long purseId) {
        this.id = purseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return description;
    }
}
