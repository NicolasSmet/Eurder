package com.application.eurder.domain;

import com.application.eurder.exceptions.FieldNullOrEmptyException;
import com.application.eurder.exceptions.FieldZeroOrLessThanZeroException;
import com.application.eurder.validation.NullOrEmptyValidator;

import java.util.UUID;

public class Item {
    private final UUID id;
    private final String name;
    private final String description;
    private final double price;
    private int amount;

    public Item(String name, String description, double price, int amount) {
        validateName(name);
        validateDescription(description);
        validatePrice(price);
        validateAmount(amount);


        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void validateName(String name){
        if(NullOrEmptyValidator.stringNullOrEmpty(name)){
            throw new FieldNullOrEmptyException("item name");
        }
    }
    public void validateDescription(String exception) {
        if (NullOrEmptyValidator.stringNullOrEmpty(exception)) {
            throw new FieldNullOrEmptyException("description");
        }
    }
    public void validatePrice(double price){
        if(price <= 0){
            throw new FieldZeroOrLessThanZeroException("price");
        }
    }
    public void validateAmount(int amount){
        if(amount <= 0){
            throw new FieldZeroOrLessThanZeroException("amount");
        }
    }
}
