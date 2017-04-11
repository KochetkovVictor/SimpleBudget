package ru.simplebudget.model.to;


public class AmountWrapper {
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public AmountWrapper(Double value) {
        this.value = value;
    }

    private Double value;
}
