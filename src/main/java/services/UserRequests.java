package services;

import models.Payload;
import models.Response;

import static io.restassured.RestAssured.given;

public class UserRequests
{
    public Response addNewUser(String name, String job)
    {
        return given()
                .contentType("application/json")
                .body(new Payload(name, job))
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .as(Response.class);
    }

    public Response updateUser(String id, String name, String newJob)
    {
        Payload updatedUser = new Payload(name, newJob);
        return given()
                .contentType("application/json")
                .body(updatedUser)
                .when()
                .put("/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .as(Response.class);
    }

    public Response getUser(String id) {
        return given()
                .when()
                .get("/{id}", id)
                .then()
                .extract()
                .as(Response.class);
    }


    public boolean deleteUser(String id)
    {
        io.restassured.response.Response response = given()
                .when()
                .delete("/{id}", id)
                .then()
                .extract()
                .response();
        int status = response.statusCode();
        return status == 204;
    }
}
