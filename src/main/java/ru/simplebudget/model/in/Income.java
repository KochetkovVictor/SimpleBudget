package ru.simplebudget.model.in;


import ru.simplebudget.model.common.Purse;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Income")
@StaticMetamodel(Income.class)
public class Income {


    @Id
    @SequenceGenerator(name = "global_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private
    Long id;

    @Column(name="datetime",columnDefinition = "timestamp default now()")
    private LocalDate incomeDate;
    private Double value;
    private String description;

    @JoinColumn(name="purseId")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private
    Purse purse;

    public Long getId() {
        return id;
    }

    public void setId(Long incomeId) {
        this.id = incomeId;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Purse getPurse() {
        return purse;
    }

    public void setPurse(Purse purse) {
        this.purse = purse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Income() {
        this.value=0.0;
        this.incomeDate =LocalDateTime.now().toLocalDate();
    }


    public Income(Long id) {
        this.id =id;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + id +
                ", incomeDate=" + incomeDate +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", purse=" + purse +
                '}';
    }
}
