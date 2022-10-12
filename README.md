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
