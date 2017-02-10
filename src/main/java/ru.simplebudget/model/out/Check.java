package ru.simplebudget.model.out;



import ru.simplebudget.model.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Noutbook on 23.01.2017.
 */
public class Check {
    Long checkId;
    Long amount;
    LocalDateTime dateTime;
    ArrayList<Product> products;
}
