# CookEggs

Bu proje **Timer**, **Intent**, **Runnable** ve **AlertDialog** kullanılarak geliştirildi.

Kısaca, istenilen yumurta kıvamına göre bir süre seçilir ve geri sayım başlar.
Süre bittiğinde 15 sn. boyunca alarm çalar. Alarm bittiğinde ise ekrana uyarı mesajı verilir.

## Geliştirme Adımları 

Öncelikle activity_main.xml dosyasına 3 adet yumurta resmi ve 3 adet butonumuzu yerleştirdik.

![deneme](https://github.com/hasanbektas1/CookEggs/blob/master/app/src/main/res/drawable/eggimage.jpeg)

```
 <ImageView
                android:id="@+id/imageView"
                android:layout_width="200dp"
                android:layout_height="200dp"
                android:paddingStart="0dp"
                app:srcCompat="@drawable/eggtime5" />
            <Button
                android:id="@+id/under"
                android:layout_width="200dp"
                android:layout_height="100dp"
                android:layout_gravity="center"
                android:backgroundTint="#C8A31B"
                android:onClick="undercooked"
                android:text="5 Minute"
                android:textSize="25sp"/>
```


Yumurta resimlerine göre az pişmiş 5 dakika, orta pişmiş 8 dakika ve çok pişmiş 12 dakika olarak belirledik.

Yukarıdaki resimde görüldüğü üzere, Button'a tıklayınca ne olacağını belirledik.
Yani; Button'ın onClick event'i tetiklendiğinde hangi functionın çalışacağını belirledik.
```android:onClick="undercooked"```
```
fun undercooked(view: View){
        val intent= Intent(applicationContext,MainActivity2::class.java)
        intent.putExtra("time",5)
        startActivity(intent)
    }
```
    
Bu fonksiyonun nasıl çalıştığını incelemeden önce ihtiyaçlarımızı belirleyelim. Yumurta seçildikten sonra yeni bir ekran açılması ve seçimimize göre sayaç başlaması gerekmektedir.
Yeni bir activity eklemek için :
MainActivitynin bulunduğu klasöre sağ tıklayıp 
> New > Activity > Gallery 
> 
Bu adımlardan sonra açılan pencerede **Empty Activity** seçip isim verip finish butonuna bastığımızda yeni boş activity eklemiş oluyoruz.
Tekrar fonksiyona dönecek olursak, yeni ekledigimiz activitye geçmek için intent tanımladık. Intent tanımlarken içine context ve activitye verdigimiz isim parametrelerini gönderiyoruz. Yeni ekrana geçiş yaparken veri göndermek istersek **intent.putExtra()** fonksiyonunu kullanabiliriz. Gönderecegimiz veriler intent içerisinde key-value şeklinde tutulur.
Fonksiyonumuzdan yeni ekledigimiz activitye gidecek verinin keyi time, valuesu 5'dir.
StartActivity içine oluşturdugumuz intenti vererek yeni ekrana geçmiş oluyoruz.

Yeni açtigımız activity için kodları inceleyelim.

```var time = intent.getIntExtra("time", 1)```
MainActivity2nin içindeki onCreate fonksiyonuna gittigimizde yukarıdaki satırı görecegiz.
Bir önceki ekrandan yolladıgımız keyi "time" olarak belirledigimiz valueyi getIntExtra() fonksiyonu ile okuyoruz.
Artık sayacımızın kaç dakika boyunca çalışacagını biliyoruz.

```
object : CountDownTimer((time * 60 * 1000).toLong(), 1000)
```
CountDownTimer 2 adet parametre alır. Birincisi geri sayımı kaçtan başlayacak, ikincisi geri sayım kaçar kaçar azalacak. Bu degerleri milisaniye cinsinden alır.

```
override fun onTick(millisUntilFinished: Long)
```
onTick fonksiyonu CountDownTimer sınıfına aittir. Yukarıdaki CountDownTimer tanımından yola çıkarsak, onTick fonksiyonumuz saniyede bir kez çalışacaktır. millisUntilFinished degişkeni sayesinde timerin bitmesine ne kadar süre kaldıgını görebiliriz.

``` 
var currentMinute = millisUntilFinished / 60000
var currentSecond = (millisUntilFinished % 60000) / 1000
```
onTick fonksiyonunun içinde saniye ve dakikayı yukarıda görüldügü gibi hesaplıyoruz

Bir diger CountDownTimera ait fonksiyon olan onFinish içinde ise geri sayımımız bitince ne olmasını istiyorsak onu yazıyoruz.
Geri sayım bitiminde alarm çalması için öncelikle mp3 uzantılı dosyamızı 
> app > res > raw 
> klasörü içine atıyoruz
Sonrasında
```
val mediaplayer = MediaPlayer.create(this@MainActivity2, R.raw.alarmmp3)
mediaplayer.start()
```
MediaPlayer.create ile ilk parametrede içinde bulunan activity ekranını, ikinci parametrede yükledigimiz ses dosyasının yolunu yazıyoruz.









Daha sonra MainActivity kısmında kullanıcının hangi yumurtayı seçecegine göre tıklanınca ne olacagı olayları yazdık
Tıklanılan yumurta ile bir sonraki activity ekranına geçilir ve geçilirken anahtar kelime ile veri gönderilir
Sonra geçilen activity ekranında gelen anahtar kelimenin verisine göre geri sayımın kaçtan başlatılacagını belirliyoruz
CountDownTimer methodunu saniye olarak hesabımızı yapıyoruz
Daha sonra azalan dakikaya göre background degişik renklendirme yapıyoruz
Dakika bitiminde alarm çalmasını saglıyoruz ve runnable yöntemi ile arkada kullanıcıya göstermeden 15 saniye saydırıp alarmı durduruyoruz
Alarmı durdurduktan sonra alertDialog yöntemi ile kullanıcıya uyarı mesajı verip yeni bir pişirme başlatıp başlatmayacgını soruyorz
İstemiyorsa program sonlanıyor istiyorsa yeniden başlangıç ekranına dönüş yapıyoruz


