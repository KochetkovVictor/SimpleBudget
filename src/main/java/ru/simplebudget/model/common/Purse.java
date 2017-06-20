package ru.simplebudget.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import ru.simplebudget.model.in.Income;
import ru.simplebudget.model.user.User;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;


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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private
    User user;


    @OneToMany(mappedBy = "purse", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    private
    Set<Income> incomes;

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Purse() {
        this.id=null;
        this.amount=0.0;
        this.active=true;
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
