package ru.simplebudget.model;

import javax.persistence.Entity;

/**
 * Created by Noutbook on 23.01.2017.
 */
@Entity
public class Purse {

    Long purseId;
    String description;
    Long amount;
}
