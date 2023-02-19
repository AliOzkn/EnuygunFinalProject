package requests;

import endpoints.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Requests {
    public Response response;
    List<Object> list;

    public Requests() {
        baseURI = "https://petstore.swagger.io/v2";
    }

    // This method lists "id" values in the response.
    public List<Object> getId(String status) {

            list = RestAssured
                .given()
                .queryParam("status", status)
                .when()
                .get(baseURI + EndPoints.findByStatus)
                .then()
                .extract().path("id");
        return list;

    }

    // This method returns results based on "status" values with GET.
    public Response findByStatus(String status) {

        response = given()
                .queryParam("status", status)
                .when()
                .get(baseURI + EndPoints.findByStatus)
                .then()
                .log().body()
                .extract().response();
        return response;
    }


    // This method returns results based on "id" values with GET.
    public Response findById(String status, int idIndex) {

        response = given()
                .pathParam("petId", getId(status).get(idIndex))
                .when()
                .get(baseURI + EndPoints.findById)
                .then()
                .log().body()
                .extract().response();
        return response;
    }

    // This method updates result based on "id" value with POST.
    public Response updatePetById(int idIndex, String name, String firstStatus, String newStatus ) {

        response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .pathParam("petId", getId(firstStatus).get(idIndex))
                .formParam("name", name)
                .formParam("status", newStatus)
                .when()
                .post(baseURI + EndPoints.updatePet)
                .then()
                .log().body()
                .extract().response();
        return response;
    }

    // This method deletes result based on "id" value with DELETE.
    public Response deletePetById(String status, int idIndex) {

        response = given()
                .pathParam("petId", getId(status).get(idIndex))
                .when()
                .delete(baseURI + EndPoints.deletePet)
                .then()
                .log().body()
                .extract().response();
        return response;
    }
}
