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
    Bu sayfadaki kodlar, int veri tipine gore yazilmistir. Eger sorgu sonucunda, id degerleri Long tipinde cikarsa;
    findPetById() metodunda bulunan, .body("id",equalTo(Integer.parseInt(firstId))) kodu; .body("id",equalTo(Long.parseLong(firstId)) olarak guncellenmelidir.
*/
public class SwaggerPetTestWithPendingParameter {
    Response response;
    String firstId;

    public SwaggerPetTestWithPendingParameter() {

        baseURI = "https://petstore.swagger.io/v2/pet";
    }

    @Test(priority = 0)
    @Story("This method gets first Id value from Get/FindByStatus request.")
    @Feature("Get first Id value")
    public void getFirstIdValue() {

        List<Object> list = RestAssured
                .given()
                .queryParam("status", "pending")
                .when()
                .get(baseURI + "/findByStatus")
                .then()
                .extract().path("id");

        firstId = String.valueOf(list.get(0));
        System.out.println("firstId : " + firstId);

    }

    @Test(priority = 1)
    @Story("This method finds pets by status as 'pending'. And puts them on a list.")
    @Feature("Find pets by status as 'pending'")
    public void findsPetsByStatus() {

        response = given()
                .queryParam("status", "pending")
                .when()
                .get(baseURI + "/findByStatus")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        assertNotNull(response.getBody());
        String status = response.path("status").toString();
        assertTrue(status.contains("pending"));
        assertEquals(response.getHeaders().getValues("Content-Type").toString(), "[application/json]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Headers").toString(), "[Content-Type, api_key, Authorization]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Methods").toString(), "[GET, POST, DELETE, PUT]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Origin").toString(), "[*]");
    }

    @Test(dependsOnMethods = "getFirstIdValue", priority = 2)
    @Story("This method finds pets by Id value")
    @Feature("Find pet by Id")
    public void findPetById() {

        response = given()
                .pathParam("petId", firstId)
                .when()
                .get(baseURI + "/{petId}")
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(firstId)))
                .body("status", equalTo("pending"))
                .contentType(ContentType.JSON)
                .extract().response();

        assertNotNull(response.getBody());
        assertEquals(response.getHeaders().getValues("Content-Type").toString(), "[application/json]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Headers").toString(), "[Content-Type, api_key, Authorization]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Methods").toString(), "[GET, POST, DELETE, PUT]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Origin").toString(), "[*]");

    }

    @Test(dependsOnMethods ="getFirstIdValue",priority =3)

    @Story("This method updates pet's name as 'Garry' and status as 'sold'")
    @Feature("Update the pet with Id")
    public void updatePetById() {

        response = given()
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .pathParam("petId", firstId)
                .formParam("name", "Garry")
                .formParam("status", "sold")
                .when()
                .post(baseURI + "/{petId}")
                .then()
                .log().body()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(firstId))
                .extract().response();

        assertNotNull(response.getBody());
        assertEquals(response.getHeaders().getValues("Content-Type").toString(), "[application/json]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Headers").toString(), "[Content-Type, api_key, Authorization]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Methods").toString(), "[GET, POST, DELETE, PUT]");
        assertEquals(response.getHeaders().getValues("Access-Control-Allow-Origin").toString(), "[*]");

    }

    @Test(dependsOnMethods = "getFirstIdValue", priority = 4)
    @Story("This method deletes pet with Id value")
    @Feature("Delete the pet with Id")
    public void deletePetById() {

        response = given()

                .pathParam("petId", firstId)
                .when()
                .delete(baseURI + "/{petId}")
                .then()
                .log().body()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                //.body("message",equalTo(firstId.toString()))
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
