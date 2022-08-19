import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class GroceryStoreTest {

    public GroceryStoreTest() {
        baseURI = "deneme";
    }
    Response response;
    JsonPath actualBody;

    @Test(priority = 1)
    public void getAllGrocery() {

        response = given()
                .when()
                .get(baseURI + "/allGrocery")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.id", hasItems(1, 2))
                .body("data.name", hasItems("apple", "grapes"))
                .body("data.price", hasItems(3, 5))
                .body("data.stock", hasItems(100, 50))
                .extract().response();

        assertNotNull(response.getBody());
    }

    @Test(priority = 2)
    public void getAllGroceryWithName() {

        response = given()
                .pathParam("name", "apple")
                .when()
                .get(baseURI + "/allGrocery" + "/{name}")
                .then()
                .log().body()
                .statusCode(200)
                .body("data[0].id", equalTo(1))
                .body("data[0].name", equalTo("apple"))
                .body("data[0].price", equalTo(3))
                .body("data[0].stock", equalTo(100))
                .contentType(ContentType.JSON)
                .extract().response();

        assertNotNull(response.getBody());
    }

    @Test(priority = 3)
    public void postAddNewProduct() throws JsonProcessingException {

        JSONObject req = new JSONObject();
        req.put("id", 4);
        req.put("name", "melone");
        req.put("price", 20);
        req.put("stock", 80);

        response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(req.toJSONString())
                .post(baseURI + "/add")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        actualBody = response.jsonPath();
        assertNotNull(response.getBody());
        assertEquals(actualBody.getInt("id"), req.get("id"));
        assertEquals(actualBody.getString("name"), req.get("name"));
        assertEquals(actualBody.getInt("price"), req.get("price"));
        assertEquals(actualBody.getInt("stock"), req.get("stock"));

    }
}
