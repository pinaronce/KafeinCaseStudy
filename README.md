# KafeinCaseStudy Otomasyon Senaryosu

## Proje Hakkında
Bu proje, **Cimri.com** üzerinden en uygun fiyatlı ürünü bulup, seçilen alışveriş sitesine sepete ekleyen bir Java Selenium otomasyon senaryosunu içermektedir. Proje, kullanıcı deneyimini artırmak ve otomatik test süreçlerini kolaylaştırmak amacıyla geliştirilmiştir.

## Gereksinimler
- Java JDK 17 veya üstü
- Maven
- Selenium WebDriver
- Log4j
- FFmpeg
- Gauge (Önerilen versiyon: 0.10.3)
- Gauge Java Plugin (Önerilen versiyon: 1.6.0)

## Kurulum

### Java JDK Kurulumu
- Java JDK'yı [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) veya [OpenJDK](https://jdk.java.net/) üzerinden indirin ve kurun.

### Maven Kurulumu
- Maven'i [Maven Resmi Sitesi](https://maven.apache.org/download.cgi) üzerinden indirin ve kurun.

### Gauge Kurulumu
- Gauge'ı aşağıdaki komut ile kurun:
  ```bash
  brew install gauge
  ```
  (Eğer Homebrew yüklü değilse, [Homebrew Kurulumu](https://brew.sh/) talimatlarını takip edin.)

### Gauge Java Eklentisini Yükleyin
- Aşağıdaki komutu çalıştırarak Gauge Java eklentisini yükleyin:
  ```bash
  gauge install java
  ```

### FFmpeg Kurulumu
- FFmpeg'i [FFmpeg Resmi Sitesi](https://ffmpeg.org/download.html) üzerinden indirin ve kurun.
  - Mac için Homebrew kullanarak FFmpeg'i yüklemek için aşağıdaki komutu çalıştırın:
    ```bash
    brew install ffmpeg
    ```

### Repository'yi Klonlayın
- Projeyi GitHub'dan klonlamak için aşağıdaki komutu kullanın:
  ```bash
  git clone https://github.com/pinaronce/KafeinCaseStudy.git
  cd KafeinCaseStudy
  ```

### Maven Bağımlılıklarını Yükleyin
- Projedeki Maven bağımlılıklarını yüklemek için gerekli komutu çalıştırın:
  ```bash
  mvn clean install
  ```

### Dummy Kullanıcı Hesabı Oluşturun
- Seçtiğiniz alışveriş sitesinde sahte bir kullanıcı hesabı oluşturun ve bu bilgileri test senaryosunda kullanın.

## Kullanım

### Test Senaryosunu Çalıştırma
- Test senaryosunu çalıştırmak için [**CaseStudy.spec**](https://github.com/pinaronce/KafeinCaseStudy/blob/master/specs/CaseStudy.spec) dosyasını kullanınız.

### Video Kaydı
- Test sırasında yapılan tüm işlemler kaydedilecek ve **.gauge** içerisindeki **videos** klasöründe saklanacaktır.

### Hata Yönetimi
- Test sırasında karşılaşılan hatalar, hata türü, zamanı ve hatanın meydana geldiği adım ile birlikte loglanacaktır.

### Sepet Kontrolü
- Sepetteki ürünlerin toplam fiyatı doğru hesaplanacak ve kontrol edilecektir. Bu işlem, **MarketPlaceShoppingCartMethods** sınıfı kullanılarak gerçekleştirilir.

#### MarketPlaceShoppingCartMethods Sınıfı
- **MarketPlaceShoppingCartMethods** sınıfı, sepetle ilgili işlemleri yönetir. Bu sınıf, ürünün fiyatını alır, stok durumunu kontrol eder ve kullanıcıya uygun miktarı ayarlar. Ayrıca, sepetin toplam fiyatını doğrulamak için de kullanılır.

##### Önemli Metotlar
- **adjustProductQuantityBasedOnStock()**: Bu metot, stok durumuna göre ürün miktarını ayarlar. Eğer stok "son 1 ürün" uyarısı veriyorsa, daha fazla ürün eklenmez. Aksi takdirde, ürün miktarı 2 olarak ayarlanır.
- **setQuantity(WebDriver driver, int quantity)**: Belirtilen miktarı sepetin miktar alanına ayarlar. Bu metot, kullanıcı etkileşimleri için birden fazla deneme yapar.
- **verifyQuantity(WebDriver driver, WebElement quantityInput, int expectedQuantity)**: Ayarlanan miktarın doğru olup olmadığını kontrol eder. Eğer miktar doğruysa, toplam fiyatın hesaplanmasını ve gösterilen fiyatın beklenen fiyatla eşleşip eşleşmediğini doğrular.

##### Fiyat Hesaplama
- Fiyat hesaplaması, ürünün orijinal fiyatı ile sepet miktarının çarpılmasıyla yapılır. Bu değer, kullanıcıya gösterilen yeni fiyat ile karşılaştırılır. Eğer fiyat doğrulaması başarısız olursa, hata mesajı loglanır.

### Tarayıcı Uyumluluğu
- Test senaryosunun farklı tarayıcılarda (Chrome, Firefox, Edge, Safari) çalışıp çalışmadığını kontrol etmek için **DriverManager** sınıfını kullanın. Bu sınıf, belirtilen tarayıcı ayarlarını yapılandırarak test ortamını başlatır.

#### DriverManager Sınıfı
- **DriverManager** sınıfı, kullanılacak tarayıcıyı belirlemek ve uygun ayarlarla WebDriver'ı başlatmak için kullanılır. Tarayıcı ayarları, **BrowserConfig** sınıfında tanımlanmıştır. Aşağıdaki tarayıcılar desteklenmektedir:
  - Chrome
  - Firefox
  - Edge
  - Safari

#### Tarayıcı Ayarları
- Varsayılan tarayıcı olarak **Chrome** ayarlanmıştır, ancak bu değeri değiştirmek için `BrowserConfig` sınıfındaki **BrowserType** enum'undan yararlanabilirsiniz. Örneğin, kullanmak istediğiniz tarayıcıyı `BrowserConfig.BrowserType` üzerinden belirleyerek projeyi farklı tarayıcılarda çalıştırabilirsiniz.

- Ayrıca, Selenium Grid ile test senaryolarını koşmak için, `DriverManager` sınıfındaki **USE_GRID_PROPERTY** sabitini kullanarak yapılandırma yapabilirsiniz. Bu özellik, testlerinizi uzaktan bir grid üzerinde çalıştırmak için `System.getProperty` aracılığıyla ayarlanabilir. Varsayılan olarak bu değer **false** olarak belirlenmiştir; ancak, bunu **true** yaparak testlerinizi Selenium Grid üzerinden çalıştırabilirsiniz. Bu ayar, geniş ölçekli test senaryoları için ideal bir çözüm sunar ve farklı tarayıcıların aynı anda test edilmesini sağlar.

## Test Adımları
- Test adımlarına erişmek için [buradan CaseStudy.spec dosyasına ulaşabilirsiniz](https://github.com/pinaronce/KafeinCaseStudy/blob/master/specs/CaseStudy.spec).
- Gauge Concept, yazılım test süreçlerini daha etkili ve verimli hale getirir, böylece ekipler arasında işbirliği sağlar. [buradan concept dosyalarına ulaşabilirsiniz](https://github.com/pinaronce/KafeinCaseStudy/blob/master/concepts).

