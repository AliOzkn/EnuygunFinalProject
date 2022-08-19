<h1 align="center"> Enuygun - Test Automation Bootcamp - Swagger Pet Store API  </h1>

### https://petstore.swagger.io/ servisini kullanarak ; 
#### 1) /pet/findByStatus servisinin available, pending,sold parametresi ile testini yazın.
<img src="C:/Users/aliozkan/Desktop/findpetbystatus.png">

#### 2) /pet/findByStatus responsundan dönen pet'lerin üçüncüsünün Id'sini /pet/{petId} get servisine pet id parametresinde göndererek testini yazın.
<img src="C:/Users/aliozkan/Desktop/findpetbyid.png">

#### 3) /pet/{petId} post, delete testlerini yazın.

<img src="C:/Users/aliozkan/Desktop/updatepetbyid.png">
<img src="C:/Users/aliozkan/Desktop/deletepetbyid.png">


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
---
### ÖNEMLİ NOT

* #### get/FindByStatus servisinden dönen id değerleri bazen int, bazen Long veri tipinde olmaktadır. Kodlar yazılırken, hangi veri tipinin kullanıldığı ve hata alınması durumunda, hangi satırın, hangi şekilde değiştirilmesi gerektiği, sınıf dosyalarında belirtilmiştir.
* #### "pending" ve "sold" parametreleriyle dönen responselarda çoğu zaman 1 veya 2 adet değer çıkmaktadır. Bu yüzden response'daki 3. id değeri yerine, 1. id değeri alınmıştır.
* #### Proje SwaggerPetTest / testng.xml dosyası ile çalıştırılabilir.
---
### ALLURE REPORT GÖRSELLERİ

<img src="C:/Users/aliozkan/Desktop/allurereport-1.png">

<img src="C:/Users/aliozkan/Desktop/allurereport-2.png">


