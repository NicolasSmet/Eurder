package com.application.eurder.controller;

import com.application.eurder.domain.User;
import com.application.eurder.domain.userdetails.Address;
import com.application.eurder.domain.userdetails.ContactDetails;
import com.application.eurder.domain.userdetails.Name;
import com.application.eurder.dto.CreateUserDTO;
import com.application.eurder.dto.UserDTO;
import com.application.eurder.repository.UserRepository;
import com.application.eurder.security.Role;
import com.application.eurder.service.UserService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @Test
    @DisplayName("When posting a user with valid data, the user can be found in the repository")
    void whenPostNewUser_thenRepositoryContainsNewUser() {
        Address validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");
        ContactDetails validContactDetails = new ContactDetails("nicolassmet2@gmail.com", validAddress, "0472454710");
        Name validName = new Name("Nicolas","Smet");

        CreateUserDTO newUser = new CreateUserDTO("Test",validContactDetails,validName, Role.CUSTOMER);

        UserDTO createdUser = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .port(port)
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDTO.class);

        Assertions.assertThat(repository.getById(createdUser.id()))
                .extracting(User::getContactdetails,User::getName)
                .containsExactly(newUser.contactDetails(),newUser.name());
    }
    @Test
    @DisplayName("When posting a user with an invalid email a EmailNotValidException is thrown and a bad request is returned")
    void whenPostNewUserWithInvalidEmail_thenEmailNotValidExceptionIsThrownAnd400Returned() {

        String testJson = """
                {
                    "password" : "test",
                    "contactDetails": {
                        "email" : "nicolassmet2gmail.com",
                        "address" : {
                            "street" : "Gravin Margaretalaan",
                            "number": "9",
                            "zipCode" : "9150",
                            "town" : "Rupelmonde"
                        },
                        "phoneNumber" : "0472454710"
                    },
                    "name" : {
                        "firstName" : "Nicolas",
                        "lastName" : "Smet"
                    },
                    "role" : "CUSTOMER"
                }""";

        String test = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(testJson)
                .when()
                .port(port)
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response()
                .asString();

        Assertions.assertThat(test).contains("email was not formatted properly");
    }


    @Test
    @DisplayName("When posting a user with an empty field in the address a FieldNotValidException is thrown and a bad request is returned")
    void whenPostNewUserWithEmptyAddressField_thenFieldNotValidExceptionIsThrownAnd400Returned() {

        String testJson = """
                {
                    "password" : "test",
                    "contactDetails": {
                        "email" : "nicolassmet2@gmail.com",
                        "address" : {
                            "street" : "Gravin Margaretalaan",
                            "number": "",
                            "zipCode" : "9150",
                            "town" : "Rupelmonde"
                        },
                        "phoneNumber" : "0472454710"
                    },
                    "name" : {
                        "firstName" : "Nicolas",
                        "lastName" : "Smet"
                    },
                    "role" : "CUSTOMER"
                }""";

        String test = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(testJson)
                .when()
                .port(port)
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response()
                .asString();

        Assertions.assertThat(test).contains("number can't be empty.");
    }
    @Test
    @DisplayName("When posting a user with no role a RoleNotValidExceptions is thrown and a bad request returned")
    void whenPostNewUserWithEmptyAddressField_thenRoleNotValidExceptionIsThrownAnd400Returned() {

        String testJson = """
                {
                    "password" : "test",
                    "contactDetails": {
                        "email" : "nicolassmet2@gmail.com",
                        "address" : {
                            "street" : "Gravin Margaretalaan",
                            "number": "9",
                            "zipCode" : "9150",
                            "town" : "Rupelmonde"
                        },
                        "phoneNumber" : "0472454710"
                    },
                    "name" : {
                        "firstName" : "Nicolas",
                        "lastName" : "Smet"
                    }
                }""";

        String body = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(testJson)
                .when()
                .port(port)
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response()
                .asString();

        Assertions.assertThat(body).contains("You did not specify a role: a role can either be CUSTOMER or ADMIN.");
    }
    @Test
    @DisplayName("When posting a new user with an email address that is not unique, an EmailNotUniqueException is thrown")
    void whenPostNewUserWithNotUniqueEmail_thenEmailNotUniqueExceptionIsThrown() {
        Address validAddress = new Address("Gravin Margaretalaan", "9", "9150", "Rupelmonde");
        ContactDetails validContactDetails = new ContactDetails("nicolassmet2@gmail.com", validAddress, "0472454710");
        Name validName = new Name("Nicolas","Smet");

        CreateUserDTO newUser = new CreateUserDTO("test",validContactDetails,validName,Role.CUSTOMER);

        service.create(newUser);

        String body = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .port(port)
                .post("/customer")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response()
                .asString();

        Assertions.assertThat(body).contains("The provided email address is not unique. Please provide a unique email address.");
    }
}