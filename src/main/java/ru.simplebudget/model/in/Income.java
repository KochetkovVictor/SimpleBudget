package ru.simplebudget.model.in;



import ru.simplebudget.model.Purse;

import java.time.LocalDateTime;


/**
 * Created by Noutbook on 23.01.2017.
 */
public class Income {

    Long incomeId;
    LocalDateTime incomeDateTime;
    Long value;
    Purse purse;
}
