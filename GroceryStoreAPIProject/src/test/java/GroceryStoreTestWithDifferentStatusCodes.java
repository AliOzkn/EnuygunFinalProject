import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class GroceryStoreTestWithDifferentStatusCodes {

    public GroceryStoreTestWithDifferentStatusCodes() {
        baseURI = "deneme";
    }
    Response response;

    @Test(priority = 1,description = "404 Not Found donmesi beklenir. Cunku /allGrocery endpointi eksik.")
    public void getAllGrocery() {

        response = given()
                .when()
                .get(baseURI)
                .then()
                .log().body()
                .statusCode(404)
                .extract().response();

        assertNull(response.getBody());
    }

    @Test(priority = 2, description = "400 Bad Request donmesi beklenir. Cunku endpoint icin gerekli olan path parametresi verilmedi.")

    public void getAllGroceryWithName() {

        response = given()
                .when()
                .get(baseURI + "/allGrocery" + "/{name}")
                .then()
                .log().body()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .extract().response();

        assertNull(response.getBody());
    }

    @Test(priority = 3,description = "405 Method Not Allowed donmesi beklenir. Cunku girdiler, makinenin anlayabilecegi sekil olan JSONObject'e cevrilmedi. ")
    public void postAddNewProduct() throws JsonProcessingException {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 4);
        map.put("name", "melone");
        map.put("price", 20);
        map.put("stock", 80);
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(map);

        response = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .body(jsonString)
                .get(baseURI + "/add")
                .then()
                .log().all()
                .statusCode(405)
                .extract().response();

        assertNull(response.getBody());

    }
}
