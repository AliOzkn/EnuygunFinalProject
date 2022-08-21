<h1 align="center"> Enuygun - Test Automation Bootcamp - UCAK BILETI SATIN ALMA  </h1>

### Test Senaryosu
#### Parametrik olarak alınacak veriler ile;
#### 1) https://www.enuygun.com/ucak-bileti/ adresine gidilir.
#### 2) (origin) Nereden Input alanına, kullanıcıdan alınan yurtiçi şehir ismi girilir. Çıkan sonuçlardan ilkine tıklanır.
#### 3) (destination) Nereye Input alanına, kullanıcıdan alınan yurtdışı şehir ismi girilir. Çıkan sonuçlardan ilkine tıklanır.
#### 4) (departureDay) Kullanıcıdan bugünü 0 alarak, kaç gün sonra gitmek istediği bilgisi alınır. Bu bilgi ile gidiş tarihi seçtirilir.
#### 5) (returnDay) Kullanıcıdan gidiş tarihini 0 alarak, kaç gün sonra dönmek istediği bilgisi alınır. Bu bilgi ile dönüş tarihi seçtirilir.
#### 6) Ucuz bilet bul butonuna tıklanır.
#### 7) (isDirect) Kullanıcıdan aktarmalı veya direkt uçuş bilgisi girilmesi istenir. Bu değer true (Direkt uçuş) veya false (Aktarmalı uçuş) olabilir. 
#### 8) (provider) Kullanıcıdan sağlayıcı bilgisi alınır. Bu bilgi ile aynı paket içerisindeki gidiş ve dönüş biletleri seçtirilir.
#### 9) Seç butonuna tıklanır.

#### Parametrik olarak alınacak veriler, src/test/resources/config.properties dosyasına yazılmalıdır. Burada ayrıca tarayıcı seçimi yapılabilmektedir. (Program Chrome, Firefox ve Edge tarayıcılarını desteklemektedir. ) Aşağıda örneği gösterilmiştir.

---

### Gidiş ve Dönüş Tarihleri Nasıl Seçildi?

#### 1) Sitede tarih seçme kısmındaki, kırmızıyla gösterilen ay ve yıl elementinin locatoru bulundu.
![calendar](https://user-images.githubusercontent.com/107454207/185793264-8edfe694-dae0-4a6b-b616-d537a8530d80.png)
```java
By monthAndYearOnCalendar = By.xpath("(//div[@class='CalendarMonth_caption CalendarMonth_caption_1'])[2]");
```
#### 2) Ay ve yıl elementinin formatına uygun şekilde, aşağıdaki kod ile firstFormat değişkeni tanımlandı. (Bu format ay adını kısaltma olmadan tamamen yazar. (Agu 2022 yerine Ağustos 2022 gibi.)
```java
SimpleDateFormat firstFormat = new SimpleDateFormat("MMMM yyyy");
```
#### 3) Sitedeki ay ve yıl elementinin text'i alındı.
```java
String departureMonthAndYearText = find(monthAndYearOnCalendar).getText();
```
#### 4) Aşağıdaki kodlar ile bugünün tarihi alındı.
```java
Calendar c1 = Calendar.getInstance();
c1.setTime(new Date());
```
#### 5) Kullanıcıdan aldığımız departureDay değeri int tipinde olmak üzere, bugünün tarihine gün olarak eklendi.
```java
c1.add(Calendar.DATE, Integer.parseInt(DriverSetup.properties.getProperty("departureDay")));
```

#### 6) Elde edilen yeni tarih, String tipindeki departureMonthAndYearValue isimli değişkene, sitedeki ay ve yıl elementinin formatıyla aynı olacak şekilde (firstFormat) atandı.
```java
String departureMonthAndYearValue = firstFormat.format(c1.getTime());
```
#### 7) Elde edilen yeni tarihin gün değeri için, onlyDayFormat isminde, sadece günü gösteren format oluşturuldu. Yeni String tipinde oluşturulan departureDay değişkeni, bu formatta gösterildi.
```java
SimpleDateFormat onlyDayFormat = new SimpleDateFormat("d");
public String departureDay;
this.departureDay = onlyDayFormat.format(c1.getTime());
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
if (departureMonthAndYearText.equals(departureMonthAndYearValue)) {
        WebElement departureDay = driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='" + this.departureDay + "']"));
        departureDay.click();
        }
```
#### 11) Gidiş tarihi aynı ay içinde olursa direk seçim yapılabilecek. Farklı aylarda olursa nasıl bir yol izlendiğine bakalım. Kırmızı ile işaretlenen "sonraki ay" butonunun locatoru bulundu.
![calendarNextMonth](https://user-images.githubusercontent.com/107454207/185793288-c47c51c2-7d55-430d-b577-24bc2e6e2a5b.png)
```java
By nextMonthBtn = By.xpath("(//div[@role='button'])[2]");
```
#### 12) if koşulu içerisindeki karşılaştırmanın sağlanmama ihtimali için, else durumu eklendi. Eğer karşılaştırma sağlanmazsa, else bloğu içerisine girip, "sonraki ay" butonuna tıklanacak.  
```java
else { click(nextMonthBtn); }
```
#### 13)While döngüsü ile de bu durum "true" olana kadar işlemler tekrarlanacak. Bütün bunları toplu olarak aşağıdaki şekilde gösterebiliriz.
```java
By departureDateInput = By.xpath("//input[@name='DepartureDate']");
By nextMonthBtn = By.xpath("(//div[@role='button'])[2]");
By monthAndYearOnCalendar = By.xpath("(//div[@class='CalendarMonth_caption CalendarMonth_caption_1'])[2]");
SimpleDateFormat firstFormat = new SimpleDateFormat("MMMM yyyy");
SimpleDateFormat onlyDayFormat = new SimpleDateFormat("d");
Calendar c1 = Calendar.getInstance();
public String departureDay;

public void selectDepartureDay(){
    c1.setTime(new Date());
    c1.add(Calendar.DATE,Integer.parseInt(DriverSetup.properties.getProperty("departureDay")));
    String departureMonthAndYearValue = firstFormat.format(c1.getTime());
    this.departureDay = onlyDayFormat.format(c1.getTime());
    
    click(departureDateInput);

    while(true){
        wait.until(ExpectedConditions.visibilityOfElementLocated(monthAndYearOnCalendar));
        String departureMonthAndYearText =f ind(monthAndYearOnCalendar).getText();

        if (departureMonthAndYearText.equals(departureMonthAndYearValue)) {
            WebElement departureDay = driver.findElement(By.xpath("(//table[@role='presentation'])[2]//tr//td//div[text()='"+this.departureDay+"']"));
            departureDay.click();
            break;
        } else {
            click(nextMonthBtn);
        }
    }
}
```
#### 14) Dönüş tarihi içinde aynı adımlar uygulanmıştır. Tek fark kullanıcıdan alınan gidiş tarihi bilgisine, dönüş tarihi bilgisi de aşağıdaki şekilde eklenmiştir.
```java
c1.add(Calendar.DATE, (Integer.parseInt(DriverSetup.properties.getProperty("departureDay"))) + (Integer.parseInt(DriverSetup.properties.getProperty("returnDay"))));
```
---
#### Projede raporlama olarak Allure Report kullanılmıştır. Terminale allure serve (allure-results dosyası xpath) yazarak ilgili sayfaya ulaşabilirsiniz.
![allure-result](https://user-images.githubusercontent.com/107454207/185796654-85c14aec-78ad-4f19-aecd-b688ef4ba1a9.png)
