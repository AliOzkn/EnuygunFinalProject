 <h1>Enuygun Flight Ticket Purchase Test</h1>
 Bu proje, <b>Patika.dev Enuygun Test Automation Bootcamp </b> bitirme projesi olarak geliştirilmiştir.

### Dizayn

- <b>pages/Locators</b> klasörü altında bulunan interface ler, elementlerin locator larını bulundurmaktadır.
- <b>TicketSettings</b> sınıfında, istenilen durumlara göre bilet seçimi için metotlar bulunmaktadır.
- <b>TicketSelect</b> sınıfında, seçilen özelliklere göre bilet alımıyla alakalı metotlar bulunmaktadır.
- <b>ExtentReporter</b> sınıfı, Extent Report için kullanılan metotu içermektedir.
- <b>utilities</b> klasöründeki sınıflarda ise driver başlatma, properties dosyası okuma gibi görevleri yapan metotlar
  bulunmaktadır.

 <hr>

### Test Senaryosu

https://www.enuygun.com/ucak-bileti/ adresinden, gidişi yurt içi, dönüşü yurt dışı bir şehir olacak şekilde; gidiş ve
dönüş tarihleri, aktarmalı veya aktarmasız uçuş durumu ve uçuş sağlayıcı seçimi yaptırılarak uçak bileti satın alma
adımlarının testi yapılmıştır.

<b>Parametrik olarak alınacak veriler, src/test/resources/config.properties dosyasında</b>  ayarlanmaktadır. Ayrıca
burada testin gerçekleştirileceği tarayıcı seçimi yapılabilmektedir.(Proje Chrome, Firefox ve Edge tarayıcılarını
desteklemektedir.) Girilecek veriler büyük, küçük harfe duyarlı değildir. Fakat Türkçe karakter kullanmayınız.<br><br>
![properties](https://user-images.githubusercontent.com/107454207/220124452-973227a6-c77b-4881-88d3-9bb6b81a0a8a.png)

* **browser &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  :** Testin gerçekleştirileceği browser. <br>
* **url &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :**
  Testin gerçekleştirileceği web sayfasının linki.<br>
* **origin &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  :** Gidiş yeri
  yazılmalıdır.<br>
* **destination &nbsp;&nbsp;&nbsp;&nbsp; :** Dönüş yeri yazılmalıdır.<br>
* **departureDay &nbsp;:** Bugünü 0 kabul ederek, bugünden kaç gün sonra gitmek istendiği yazılmalıdır.<br>
* **returnDay &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :** Gidiş tarihini 0 kabul ederek, gidiş tarihinden kaç gün sonra
  dönülmek istediğini yazılmalıdır.<br>
* **isDirect &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :** Aktarmalı veya direkt uçuş isteği
  belirtilmelidir. Bu değer boolean veri tipindedir. Eğer direkt uçuş isteniyorsa true, aktarmalı uçuş isteniyorsa false
  yazılmalıdır.<br>

<hr>
<h3>Kullanılan Teknolojiler</h3>
Java, Selenium WebDriver, TestNG, Maven

<hr>
<h3> Test Sonuçları </h3>

Raporlama olarak <b> Extent Report </b> kullanılmıştır. <b> reports </b> klasöründeki <b> index.html </b> dosyası ile
sonuca ulaşılabilir.<br>
<p align="center">
 <img src="https://user-images.githubusercontent.com/107454207/220122988-9c40ee69-accc-4cd4-a544-ee12afbb3580.png"/>
</p>

<br>

<details> <summary>  <b>Gidiş ve Dönüş tarihlerinin nasıl seçildiğini okumak için tıklayınız.</b> </summary> 

#### 1) Sitede tarih seçme kısmındaki, kırmızıyla gösterilen ay ve yıl elementinin locatoru bulundu.

![calendar](https://user-images.githubusercontent.com/107454207/185793264-8edfe694-dae0-4a6b-b616-d537a8530d80.png)

```java
By monthAndYearOnCalendar=By.xpath("(//div[@class='CalendarMonth_caption CalendarMonth_caption_1'])[2]");
```

#### 2) Ay ve yıl elementinin formatına uygun şekilde, aşağıdaki kod ile monthAndYearFormat değişkeni tanımlandı. (Bu format ay adını kısaltma olmadan tamamen yazar. (Agu 2022 yerine Ağustos 2022 gibi.)

```java
SimpleDateFormat monthAndYearFormat=new SimpleDateFormat("MMMM yyyy");
```

#### 3) Sitedeki ay ve yıl elementinin text'i alındı.

```java
String departureMonthAndYearText = driver.findElement(monthAndYearOnCalendar).getText();
```

#### 4) Aşağıdaki kodlar ile bugünün tarihi alındı.

```java
Calendar c1=Calendar.getInstance();
        c1.setTime(new Date());
```

#### 5) Kullanıcıdan aldığımız departureDay değeri int tipinde olmak üzere, bugünün tarihine gün olarak eklendi.

```java
c1.add(Calendar.DATE,Integer.parseInt(DriverSetup.properties.getProperty("departureDay")));
```

#### 6) Elde edilen yeni tarih, String tipindeki departureMonthAndYearValue isimli değişkene, sitedeki ay ve yıl elementinin formatıyla aynı olacak şekilde (monthAndYearFormat) atandı.

```java
String departureMonthAndYear=monthAndYearFormat.format(c1.getTime());
```

#### 7) Elde edilen yeni tarihin gün değeri için, onlyDayFormat isminde, sadece günü gösteren format oluşturuldu. Yeni String tipinde oluşturulan departureDay değişkeni, bu formatta gösterildi.

```java
SimpleDateFormat onlyDayFormat=new SimpleDateFormat("d");
public String departureDay;
        this.departureDay=onlyDayFormat.format(c1.getTime());
```

#### 8)Takvim üzerindeki günlerden bir tanesinin locatoru bulundu. Örneğin ayın 1. günü için locator şu şekildedir:

```java
(//table[@role='presentation'])[2]//tr//td//div[text()='1']
```

#### 9) Buradaki "1" değeri yerine, yeni elde ettiğimiz tarihten, onlyDayFormat'ı ile aldığımız gün değeri olan this.departureDay yazıldı. Bu sayede kullanıcıdan alınan veri ile takvim üzerinde gün seçimi otomatik olarak yapılacaktır.

```java
(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.departureDay + "']
```

#### 10) if koşulu içerisinde departureMonthAndYearText değeri ile departureMonthAndYearValue değeri karşılaştırıldı. Bir önceki adımda bulunan gün değeri burada kullanıldı.

```java
if(departureMonthAndYearText.equals(departureMonthAndYear)){
        WebElement departureDay=driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='"+this.departureDay+"']"));
        departureDay.click();
        }
```

#### 11) Gidiş tarihi aynı ay içinde olursa direk seçim yapılabilecek. Farklı aylarda olursa nasıl bir yol izlendiğine bakalım. Kırmızı ile işaretlenen "sonraki ay" butonunun locatoru bulundu.

![calendarNextMonth](https://user-images.githubusercontent.com/107454207/185793288-c47c51c2-7d55-430d-b577-24bc2e6e2a5b.png)

```java
By nextMonthBtn=By.xpath("(//div[@role='button'])[2]");
```

#### 12) if koşulu içerisindeki karşılaştırmanın sağlanmama ihtimali için, else durumu eklendi. Eğer karşılaştırma sağlanmazsa, else bloğu içerisine girip, "sonraki ay" butonuna tıklanacak.

```java
else{click(nextMonthBtn);}
```

#### 13)While döngüsü ile de bu durum "true" olana kadar işlemler tekrarlanacak.

#### 14) Dönüş tarihi içinde aynı adımlar uygulanmıştır. Tek fark kullanıcıdan alınan gidiş tarihi bilgisine, dönüş tarihi bilgisi de aşağıdaki şekilde eklenmiştir.

```java
c1.add(Calendar.DATE,(Integer.parseInt(DriverSetup.properties.getProperty("departureDay")))+(Integer.parseInt(DriverSetup.properties.getProperty("returnDay"))));
```
