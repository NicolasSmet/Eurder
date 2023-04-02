package com.application.eurder.domain;

import com.application.eurder.exceptions.FieldNullOrEmptyException;
import com.application.eurder.exceptions.FieldZeroOrLessThanZeroException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ItemTest {
    private final String validName = "validName";
    private final String validDescription = "validDescription";
    private final double validPrice = 10.0;
    private final int validAmount = 5;

    @Test
    @DisplayName("Creating item with empty name throws a FieldNullOrEmptyException")
    void givenEmptyName_whenCreatingItem_thenFieldNullOrEmptyExceptionIsThrown() {
        String name = "";

        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Item(name,validDescription,validPrice,validAmount))
                .withMessageContaining("item name can't be empty");
    }
    @Test
    @DisplayName("Creating item with empty description throws a FieldNullOrEmptyException")
    void givenEmptyDescription_whenCreatingItem_thenFieldNullOrEmptyExceptionIsThrown() {
        String description = "";

        Assertions.assertThatExceptionOfType(FieldNullOrEmptyException.class)
                .isThrownBy(() -> new Item(validName,description,validPrice,validAmount))
                .withMessageContaining("description can't be empty");
    }
    @Test
    @DisplayName("Creating item with price zero or less than zero throws a FieldZeroOrLessThanZeroException")
    void givenPriceZeroOrLessThanZero_whenCreatingItem_thenFieldZeroOrLessThanZeroExceptionIsThrown() {
        double price = 0;

        Assertions.assertThatExceptionOfType(FieldZeroOrLessThanZeroException.class)
                .isThrownBy(() -> new Item(validName,validDescription,price,validAmount))
                .withMessageContaining("price can't be zero or less than zero");
    }
    @Test
    @DisplayName("Creating item with amount zero or less than zero throws a FieldZeroOrLessThanZeroException")
    void givenAmountZeroOrLessThanZero_whenCreatingItem_thenFieldZeroOrLessThanZeroExceptionIsThrown() {
        int amount = 0;

        Assertions.assertThatExceptionOfType(FieldZeroOrLessThanZeroException.class)
                .isThrownBy(() -> new Item(validName,validDescription,validPrice,amount))
                .withMessageContaining("amount can't be zero or less than zero");
    }
}
