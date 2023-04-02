package com.application.eurder.controller;

import com.application.eurder.domain.Item;
import com.application.eurder.dto.CreateItemDTO;
import com.application.eurder.dto.ItemDTO;
import com.application.eurder.repository.ItemRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository repository;

    @Test
    @DisplayName("When posting an item with valid data, the item can be found in the itemrepository")
    void whenPostNewItem_thenRepositoryContainsNewItem() {

        CreateItemDTO newItem = new CreateItemDTO("new item", "A new item for our shop", 12.3, 5);

        ItemDTO createdItem = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization","Basic QWRtaW5AZ21haWwuY29tOlRlc3Q=")
                .body(newItem)
                .log().all()
                .when()
                .port(port)
                .log().all()
                .post("/item")
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ItemDTO.class);

        Assertions.assertThat(repository.getAll().size()).isEqualTo(1);
        Assertions.assertThat(repository.getById(createdItem.getId()))
                .extracting(Item::getName, Item::getDescription, Item::getPrice)
                .containsExactly(newItem.getName(), newItem.getDescription(), newItem.getPrice());
    }

    @Test
    @DisplayName("When posting an item with an empty name a FieldNotValidException is thrown and a bad request is returned")
    void whenPostNewItemEmptyName_thenFieldNotValidExceptionIsThrownAnd400Returned() {

        String testJson = """
                {
                    "name" : null,
                    "description" : "a new item for our shop",
                    "price" : 12.3,
                    "amount" : 5
                }""";

        String test = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization","Basic QWRtaW5AZ21haWwuY29tOlRlc3Q=")
                .body(testJson)
                .when()
                .port(port)
                .post("/item")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response()
                .asString();

        Assertions.assertThat(test).contains("item name can't be empty");
    }
    @Test
    @DisplayName("When posting an item as an unknown user a UserUnknownException is thrown and forbidden is returned")
    void whenPostNewItemAsUnknownUser_thenUserUnknownExceptionIsThrownAnd403Returned() {

        String testJson = """
                {
                    "name" : null,
                    "description" : "a new item for our shop",
                    "price" : 12.3,
                    "amount" : 5
                }""";

        String test = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization","Basic dGVzdEBnbWFpbC5jb206VGVzdA==")
                .body(testJson)
                .when()
                .port(port)
                .post("/item")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract()
                .response()
                .asString();

        Assertions.assertThat(test).contains("The provided user is not known, please provide a known user.");
    }
    @Test
    @DisplayName("When posting an item as an invalid authorization format a FieldFormatNotValidException is thrown and forbidden is returned")
    void whenPostNewItemWithInvalidAuthorization_thenFieldNullOrEmptyExceptionIsThrownAnd403Returned() {

        String testJson = """
                {
                    "name" : null,
                    "description" : "a new item for our shop",
                    "price" : 12.3,
                    "amount" : 5
                }""";

        String test = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "test1234")
                .body(testJson)
                .when()
                .port(port)
                .post("/item")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .extract()
                .response()
                .asString();

        Assertions.assertThat(test).contains("authorization was not formatted properly");
    }
}
