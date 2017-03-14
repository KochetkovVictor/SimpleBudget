package ru.simplebudget.model.in;



import ru.simplebudget.model.common.Purse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@Entity
@Table(name="Income")
@StaticMetamodel(Income.class)
public class Income {

    @Id
    private
    Long incomeId;
    private LocalDateTime incomeDateTime;
    private Long value;
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Purse getPurse() {
        return purse;
    }

    public void setPurse(Purse purse) {
        this.purse = purse;
    }

    public Income() {

    }
}
