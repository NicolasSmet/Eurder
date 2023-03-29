package com.application.eurder.validation;

public class EmptyStringValidator{

    public static boolean stringNullOrEmpty(String toValidate) {
        return toValidate == null || toValidate.trim().isEmpty();

    }
}
