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

        CreateItemDTO newItem = new CreateItemDTO("new item","A new item for our shop",12.3, 5);

        ItemDTO createdItem = RestAssured
                .given()
                .contentType(ContentType.JSON)
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
                .containsExactly(newItem.getName(),newItem.getDescription(),newItem.getPrice());
    }
}
