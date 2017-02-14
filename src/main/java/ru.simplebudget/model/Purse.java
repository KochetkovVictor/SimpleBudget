package ru.simplebudget.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Noutbook on 23.01.2017.
 */
@Entity
@Table(name="Purse")
public class Purse {

    Long purseId;
    String description;
    Long amount;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
