package com.application.eurder.security;

import com.application.eurder.domain.User;
import com.application.eurder.exceptions.UnauthorizedException;
import com.application.eurder.exceptions.UserUnknownException;
import com.application.eurder.exceptions.WrongPasswordException;
import com.application.eurder.repository.UserRepository;
import com.application.eurder.validation.AuthorizationStringValidator;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    public SecurityService(UserRepository repository){
        this.repository = repository;
    }
    private final UserRepository repository;
    public void validateAuthorization(String authorization, Feature feature) {
        Credentials credentials = getCredentialsFromAuthorization(authorization);
        User user = repository.getByEmail(credentials.getEmail());

        if(user == null){
            throw new UserUnknownException();
        }
        if(!user.getPassword().equals(credentials.getPassword())){
            throw new WrongPasswordException();
        }
        if(!user.hasAccessTo(feature)){
            throw new UnauthorizedException();
        }
    }

    public Credentials getCredentialsFromAuthorization(String authorization) {
        AuthorizationStringValidator.validateAuthorizationFormat(authorization);

        String decodedAuthorization = decodeAuthorization(authorization);

        AuthorizationStringValidator.validateDecodedAuthorizationFormat(decodedAuthorization);

        return extractCredentialsFromDecodedAuthorization(decodedAuthorization);
    }

    private String decodeAuthorization(String authorization) {
        return new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
    }

    public Credentials extractCredentialsFromDecodedAuthorization(String decodedAuthorization) {

        String userEmail = decodedAuthorization.substring(0, decodedAuthorization.indexOf(":"));
        String userPassword = decodedAuthorization.substring(decodedAuthorization.indexOf(":") + 1);

        return new Credentials(userEmail, userPassword);
    }
}
