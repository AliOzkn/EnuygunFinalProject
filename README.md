 <h1>Enuygun Flight Ticket Purchase Test</h1>
 Bu proje, <b>Patika.dev Enuygun Test Automation Bootcamp </b> bitirme projesi olarak geliştirilmiştir.
 
 ### Test Senaryosu
https://www.enuygun.com/ucak-bileti/ adresinden, gidişi yurt içi, dönüşü yurt dışı bir şehir olacak şekilde; gidiş ve dönüş tarihleri, aktarmalı veya aktarmasız uçuş durumu ve uçuş sağlayıcı seçimi yaptırılarak uçak bileti satın alma adımlarının testi yapılmıştır.

 <b>Parametrik olarak alınacak veriler, src/test/resources/config.properties dosyasında</b>  ayarlanmaktadır. Ayrıca burada testin gerçekleştirileceği tarayıcı seçimi yapılabilmektedir.(Proje Chrome, Firefox ve Edge tarayıcılarını desteklemektedir.) Girilecek veriler büyük, küçük harfe duyarlı değildir. Fakat Türkçe karakter kullanmayınız.<br><br>
 ![properties](https://user-images.githubusercontent.com/107454207/185840170-fb082bbf-b046-4647-a2b8-126a6d4a980c.png)
 
* **browser &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  :** Testin gerçekleştirileceği browser. <br>
* **url &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :** Testin gerçekleştirileceği web sayfasının linki.<br>
* **origin &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  :** Gidiş yazılmalıdır.<br>
* **destination &nbsp;&nbsp;&nbsp;&nbsp; :** Dönüş yeri yazılmalıdır.<br>
* **departureDay &nbsp;:** Bugünü 0 kabul ederek, bugünden kaç gün sonra gitmek istendiği yazılmalıdır.<br>
* **returnDay &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :** Gidiş tarihini 0 kabul ederek, gidiş tarihinden kaç gün sonra dönmek istediğini yazılmalıdır.<br>
* **provider &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :** Uçuş sağlayıcı ismi girilmelidir.<br>
* **isDirect &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :** Aktarmalı veya direkt uçuş isteği belirtilmelidir. Bu değer boolean veri tipindedir. Eğer direkt uçuş isteniyorsa true, aktarmalı uçuş isteniyorsa false yazılmalıdır.<br>

<hr>
<h3> Projenin Çalıştırılması </h3><br>
<b>1)</b> Projenin çalıştırılacağı bilgisayarda Java SDK ve Apache Maven kurulu olmalıdır. Ayrıca raporlamanın stabil çalışması için Allure CommandLine yüklenmelidir. <br>
<b>2)</b></b> Bu bir <b> Maven </b> projesidir. Programın ilk başta çalışmaması halinde, tek yapılması gereken <b> pom.xml </b> dosyasını güncellemektir. <br>
<b> pom.xml </b> dosyası açıldıktan sonra, Intellij için sağ tarafta bulunan Maven sekmesi açılarak aşağıda kırmızıyla gösterilen <b> update </b> simgesine tıklamak yeterlidir.<br>

![maven update](https://user-images.githubusercontent.com/107454207/195383550-c5d31544-5950-489d-b85b-10fa0e4de831.png)


<b>3)</b> Proje, <b> src/test/java/testscenarios/testng.xml </b> dosyasından çalıştırılmalıdır.

<hr>
<h3> Test Sonuçları </h3><br>

Bu projede, raporlama olarak <b> Allure Report </b> kullanılmıştır. Terminale ```allure serve (allure-results dosyası xpath)``` yazılarak ilgili sayfaya ulaşılabilir.<br>
![allure-result](https://user-images.githubusercontent.com/107454207/185796654-85c14aec-78ad-4f19-aecd-b688ef4ba1a9.png)

<hr>
<hr>
<hr>
<h1>Swagger PetStore API Test</h1>
### Test Senaryosu
https://petstore.swagger.io/ servisi kullanılarak,<br>
<b>1)</b> /pet/findByStatus servisinin available, pending,sold parametresi ile testleri yazılır.<br>

![1](https://user-images.githubusercontent.com/107454207/195404580-50533df5-b8fa-4e33-a852-f1dbc3c74649.png) <br>

<b>2)</b>/pet/findByStatus responsundan dönen pet'lerin üçüncüsünün Id'sini /pet/{petId} get servisine pet id parametresinde göndererek testi yazılır.<br>

![2](https://user-images.githubusercontent.com/107454207/195404811-62fbe607-decf-4d97-aad8-77e195040a65.png) <br>

<b>3)</b> /pet/{petId} post methodu için test yazılır.<br>

![3](https://user-images.githubusercontent.com/107454207/195404830-5c5e905b-263c-47cd-98fe-e401a6574b87.png) <br>

<b>4)</b> /pet/{petId} delete methodu için test yazılır.<br>

![4](https://user-images.githubusercontent.com/107454207/195404833-9e1e801e-f345-43f2-9466-4e2d9db11799.png) <br>

---
### ÖNEMLİ NOT

*  <b>get/FindByStatus</b> servisinden dönen <b> id </b> değerleri bazen <b>int</b>, bazen <b> Long</b> veri tipinde olmaktadır. Kodlar yazılırken, hangi veri tipinin kullanıldığı ve hata alınması durumunda, hangi satırın, hangi şekilde değiştirilmesi gerektiği, sınıf dosyalarında belirtilmiştir.
*  "pending" ve "sold" parametreleriyle dönen responselarda çoğu zaman 1 veya 2 adet değer çıkmaktadır. Bu yüzden response'daki 3. id değeri yerine, 1. id değeri alınmıştır.
*  Proje SwaggerPetTest / testng.xml dosyası ile çalıştırılabilir.
---
### Test Sonuçları

![allurereport-1](https://user-images.githubusercontent.com/107454207/185461977-fbd7f42a-9ed3-4001-99ee-080e5da1f8c2.png)

![allurereport-2](https://user-images.githubusercontent.com/107454207/185462084-79304941-2783-4266-9ece-4fbbd159a960.png)

