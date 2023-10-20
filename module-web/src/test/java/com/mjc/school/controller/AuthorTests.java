package com.mjc.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class AuthorTests {
    private final String BASE = "http://localhost:8080/";

    @Test
    public void createAuthorTest() {
        String authorName = "testAuthor";

        JSONObject reqBody = new JSONObject();
        reqBody.put("name", authorName);

        given().contentType("application/json").body(reqBody.toString())
                .when().post(BASE + "authors/create")
                .then()
                .statusCode(200)
                .body("name", equalTo(authorName))
                .body("id", notNullValue());
    }

    @Test
    public void readAuthorByIdTest() {

    }

    @Test
    public void readAllAuthorsTest() {

    }

    public void updateAuthorTest() {

    }

    @Test
    public void deleteAuthorTest() {
        String authorName = "testAuthor";

        JSONObject createReq = new JSONObject();
        createReq.put("name", authorName);

        var authorId = given().contentType("application/json").body(createReq.toString())
                .when().post(BASE + "authors/create")
                .then()
                .statusCode(200)
                .body("name", equalTo(authorName))
                .body("id", notNullValue())
                .extract()
                .path("id");
        
        given()
                .when().delete(BASE + "authors/" + authorId +"/delete")
                .then()
                .statusCode(200);
    }
}
