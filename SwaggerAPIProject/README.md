#### This code keeps the "id" values in the response obtained from the query in the form a list. The value in the desired index can be used in other queries.
```java

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
```
<br><br>
- In EndPoints class, there are different endpoints for different queries.
- In Requests class, there are commands for GET, POST, DELETE methods.
- In Tests folder, there are different tests created for the "available","pending" and "sold" parameters.
<br><br><br>
 <p align="center">
 <img src="https://user-images.githubusercontent.com/107454207/219943734-bd3fd67e-7a72-4ea8-9816-03ef9dcb2c19.png"/>
  </p>






