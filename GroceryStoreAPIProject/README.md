<h1 align="center"> Enuygun - Test Automation Bootcamp - Grocery Store API  </h1>

### Meyve ve sebze satan bir işletmenin api servis testlerini yazdığınızı düşünün.

#### 1) Stok fiyat bilgisi dönen bir endpoint : GET/allGrocery

```
{
"data": [
{ "id": 1, "name": "apple", "price": 3, "stock": 100 },
{ "id": 2, "name": "grapes", "price": 5, "stock": 50 } ]
}
```

---

#### 2) İsme göre cevap dönen bir endpoint : GET/allGrocery/{name}

```
{
"data": [
{ "id": 1, "name": "apple", "price": 3, "stock": 100 } ]
}
```

---

#### 3) Yeni ürün ekleyebildiğimiz bir endpoint : POST/add

```
{ "id": 4, "name": "string", "price": 12.3, "stock": 3 }
```

---

### Bu bilgilere göre REST ile en az 3 api testi yazınız. Farklı 200, 400, 404 gibi farklı http status kodları karşılayabilmesi iyi olur.

----
----

### Senaryo 1
#### Stok bilgisini görebilmek için Rest Assured ile basit bir kod yazılmıştır. Yukarıda belirtilen, sorgu sonucu görülmesi istenen değerlerin doğruluğu, .body() satırları içerisinde kontrol edilmiştir.
#### 

```java
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
```
### Senaryo 2
#### Verilen isme göre sonuç döndüren bu kod parçasında, "name" adında path parametresi ilave edilmiştir.  
```java
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
```

### Senaryo 3
#### Yeni bir ürün eklemek amacıyla POST metoduyla kod yazılmıştır. Bu metodun, bir gövdeye sahip olması gerektiği için ilk olarak yeni eklenecek ürün özellikleri belirtilmelidir. Ekleme işlemini yapmanın farklı yolları vardır, bunlardan ikisi aşağıda gösterilmiştir;

* Map içerisine objeler atayabiliriz. Atadığımız objeleri, ObjectMapper veya JSONObject ile JsonString'e dönüştürerek test kodu içerisinde kullanabiliriz.
  
```java
  Map<String, Object> map = new HashMap<String, Object>();
  map.put("id", 4);
  map.put("name", "melone");
  map.put("price", 20);
  map.put("stock", 80);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(map);
        // OR
        JSONObject request = new JSONObject(map);
```
* Daha basit bir şekilde sadece JSONObject kullanarak aynı işlem yapılabilir.
```java
    JSONObject req = new JSONObject();
    req.put("id", 4);
    req.put("name", "melone");
    req.put("price", 20);
    req.put("stock", 80);
```
Bu senaryoda ikinci yöntem kullanılarak aşağıdaki test kodu yazılmıştır. assertEquals ile body içerisine koyduğumuz değerler ve sonuçta elde edilen değerler karşılaştırılmıştır.
```java
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
``` 
----
----
Test yaparken karşılaşabileceğimiz olumsuz Status kodlarından bazıları kısaca şöyle açıklanabilir:

* 204 (No Content)  : İstek alınmıştır fakat body'de dönen bir değer yoktur.
* 400 (Bad Request) : Eksik veri, yanlış syntax gibi nedenlerden dolayı meydana gelir.
* 401 (Unauthorized) : Kimlik doğrulama, yetkilendirme gereklidir.
* 403 (Forbidden) : 401'e benzer olarak istemcinin geçerli bir veriye sahip olduğu ancak sunucu tarafından erişimin reddedildiği anlamına gelir.
* 404 (Not Found) : İstenen kaynağın sunucu tarafından bulunamayacağı anlamına gelir.
* 405 (Method Not Allowed) : İstenen bir yöntemin, sunucu tarafından tanındığında bile istenen kaynak için desteklenmediği anlamına gelir. Kaynak bir GET veya POST yöntemi bekliyor olabilir, ancak bir DELETE veya PUT yöntemi alırsa, yapılan istek 405 olarak reddedilecektir.
* 429 (Too Many Request): Kullanıcı, belirli bir zaman içinde çok fazla istek göndermiştir.

Bu status kodlarıyla alakalı aşağıdaki örnekler gösterilebilir:

```java
//404 Not Found donmesi beklenir. Cunku /allGrocery endpointi eksik.
@Test
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
```
```java
//400 Bad Request donmesi beklenir. Çünkü endpoint icin gerekli olan path parametresi verilmedi.
@Test
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
```
```java  
//405 Method Not Allowed donmesi beklenir. Cunku POST metodu kullanılması gerekirken, GET metodu kullanıldı. 
@Test
public void postAddNewProduct() {

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
                .body(map)
                .get(baseURI + "/add")
                .then()
                .log().all()
                .statusCode(405)
                .extract().response();

        assertNull(response.getBody());

    }

