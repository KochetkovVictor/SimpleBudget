package ru.simplebudget.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Not enough money")
public class NotEnoughMoneyException extends RuntimeException{
    public NotEnoughMoneyException(String message){super(message);}

}
