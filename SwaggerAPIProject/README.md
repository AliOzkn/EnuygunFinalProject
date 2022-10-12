#### Available, pending ve sold parametreleri için 3 ayrı sınıf oluşuturulmuştur. Aşağıdaki kod parçası, get/pet/findByStatus sorgusundan dönen response'u List olarak alarak, listenin 3. öğesinin id değerini almamızı sağlamaktadır. Bu değer, diğer sorgularda kullanılacaktır.
```java
String thirdId;

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
```



