package services;

import io.restassured.response.Response;
import models.UserRequestPayload;
import models.UsersResponseBody;

import static io.restassured.RestAssured.given;

public class UserApiRequests
{
    public UsersResponseBody addNewUser(String name, String job)
    {
        return given()
                .contentType("application/json")
                .body(new UserRequestPayload(name, job))
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .as(UsersResponseBody.class);
    }

    public UsersResponseBody updateUser(String id, String name, String newJob)
    {
        UserRequestPayload updatedUser = new UserRequestPayload(name, newJob);
        return given()
                .contentType("application/json")
                .body(updatedUser)
                .when()
                .put("/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .as(UsersResponseBody.class);
    }

    public UsersResponseBody getUser(String id) {
        return given()
                .when()
                .get("/{id}", id)
                .then()
                .extract()
                .as(UsersResponseBody.class);
    }


    public boolean deleteUser(String id)
    {
        Response response = given()
                .when()
                .delete("/{id}", id)
                .then()
                .extract()
                .response();
        int status = response.statusCode();
        return status == 204;
    }
}
