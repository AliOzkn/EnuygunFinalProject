package SwaggerPetTest;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

/*
    Bu sayfadaki kodlar, Long veri tipine gore yazilmistir. Eger sorgu sonucunda, id degerleri int tipinde cikarsa;
    findPetById() metodunda bulunan, .body("id",equalTo(Long.parseLong(thirdId))) kodu; .body("id",equalTo(Integer.parseInt(thirdId))) olarak guncellenmelidir.*/

public class SwaggerPetTestWithAvailableParameter {
    Response response;
    String thirdId;

    public SwaggerPetTestWithAvailableParameter() {
        baseURI = "https://petstore.swagger.io/v2/pet";
    }

    @Test(priority = 0)
    @Story("This method gets third Id value from Get/FindByStatus request.")
    @Feature("Get third Id value")
    public void getThirdIdValue() {

        List<Object> list = RestAssured
                .given()
                .queryParam("status", "available")
                .when()
                .get(baseURI + "/findByStatus")
                .then()
                .extract().path("id");

        thirdId = String.valueOf(list.get(2));
        System.out.println("thirdId : " + thirdId);

    }

    @Test(priority = 1)
    @Story("This method finds pets by status as 'available'. And puts them on a list.")
    @Feature("Find pets by status as 'available'")
    public void findPetsByStatus() {

        response = given()
                .queryParam("status", "available")
                .when()
                .get(baseURI + "/findByStatus")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        assertNotNull(response.getBody());
        String status = response.path("status").toString();
        assertTrue(status.contains("available"));
        assertEquals(response.getHeaders().getValues("Content-Type").toString(), "[application/json]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Headers").toString(), "[Content-Type, api_key, Authorization]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Methods").toString(), "[GET, POST, DELETE, PUT]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Origin").toString(), "[*]");
    }

    @Test(dependsOnMethods ="getThirdIdValue", priority = 2)
    @Story("This method finds pets by Id value")
    @Feature("Find pet by Id")
    public void findPetById() {

        response = given()
                .pathParam("petId", thirdId)
                .when()
                .get(baseURI + "/{petId}")
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(Long.parseLong(thirdId)))
                .body("status", equalTo("available"))
                .contentType(ContentType.JSON)
                .extract().response();

        assertNotNull(response.getBody());
        assertEquals(response.getHeaders().getValues("Content-Type").toString(), "[application/json]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Headers").toString(), "[Content-Type, api_key, Authorization]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Methods").toString(), "[GET, POST, DELETE, PUT]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Origin").toString(), "[*]");
    }

    @Test(dependsOnMethods ="getThirdIdValue",priority = 3)
    @Story("This method updates pet's name as 'Garry' and status as 'pending'")
    @Feature("Update the pet with Id")
    public void updatePetById() {

        response = given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .pathParam("petId", thirdId)
                .formParam("name", "Garry")
                .formParam("status", "pending")
                .when()
                .post(baseURI + "/{petId}")
                .then()
                .log().body()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(thirdId))
                .extract().response();

        assertNotNull(response.getBody());
        assertEquals(response.getHeaders().getValues("Content-Type").toString(), "[application/json]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Headers").toString(), "[Content-Type, api_key, Authorization]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Methods").toString(), "[GET, POST, DELETE, PUT]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Origin").toString(), "[*]");

    }

    @Test(dependsOnMethods ="getThirdIdValue",priority = 4)
    @Story("This method deletes pet with Id value")
    @Feature("Delete the pet with Id")
    public void deletePetById() {

        response = given()
                .pathParam("petId", thirdId)
                .when()
                .delete(baseURI + "/{petId}")
                .then()
                .log().body()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(thirdId))
                .contentType(ContentType.JSON)
                .extract().response();

        assertNotNull(response.getBody());
        assertEquals(response.getHeaders().getValues("Content-Type").toString(), "[application/json]");
        assertEquals(response.getHeaders().getValues("Connection").toString(), "[keep-alive]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Headers").toString(), "[Content-Type, api_key, Authorization]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Methods").toString(), "[GET, POST, DELETE, PUT]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Origin").toString(), "[*]");
    }
}
