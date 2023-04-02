package com.application.eurder.validation;

import com.application.eurder.exceptions.FieldFormatNotValidException;

public class AuthorizationStringValidator {
    public static void validateAuthorizationFormat(String authorization){
        if(!authorization.startsWith("Basic ")){
            throw new FieldFormatNotValidException("authorization");
        }
    }
    public static void validateDecodedAuthorizationFormat(String decodedAuthorization) {
        if (!decodedAuthorization.contains(":")) {
            throw new FieldFormatNotValidException("authorization");
        }
    }
}
