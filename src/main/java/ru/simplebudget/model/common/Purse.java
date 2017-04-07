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
    Long purseId;
    @Column(name = "description", unique = true)
    @NotEmpty
    private
    String description;
    @Column(name = "amount")
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
    }

    public Long getPurseId() {
        return purseId;
    }

    public void setPurseId(Long purseId) {
        this.purseId = purseId;
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
}
