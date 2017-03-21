package ru.simplebudget.model.in;


import ru.simplebudget.model.common.Purse;

import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;
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
    Long incomeId;

    @Column(name="datetime",columnDefinition = "timestamp default now()")
    private LocalDateTime incomeDateTime;
    private Double value;
    private String description;

    @JoinColumn(name="purseId")
    @ManyToOne
    private
    Purse purse;

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public LocalDateTime getIncomeDateTime() {
        return incomeDateTime;
    }

    public void setIncomeDateTime(LocalDateTime incomeDateTime) {
        this.incomeDateTime = incomeDateTime;
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
        this.incomeDateTime=LocalDateTime.now();
    }

    /*public Income(Double value, String description) {
        this.incomeDateTime=LocalDateTime.now();
        this.value = value;
        this.description = description;
    }*/

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + incomeId +
                ", incomeDateTime=" + incomeDateTime +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", purse=" + purse +
                '}';
    }
}
