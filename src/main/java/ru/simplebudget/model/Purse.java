package ru.simplebudget.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Table(name = "purse")
public class Purse {

    @Id
    @SequenceGenerator(name = "purse_id_seq", sequenceName = "purse_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purse_id_seq")
    @Column(name = "id")
    private
    Long purseId;
    @Column(name = "description", unique = true)
    @NotEmpty
    private
    String description;
    @Column(name = "amount")
    private
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
