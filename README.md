# CookEggs   ![deneme](https://github.com/hasanbektas1/CookEggs/blob/master/app/src/main/res/drawable/eggimage.jpeg)

Bu proje **Timer**, **Intent**, **Runnable** ve **AlertDialog** kullanılarak geliştirildi.

Kısaca, istenilen yumurta kıvamına göre bir süre seçilir ve geri sayım başlar.
Süre bittiğinde 15 sn. boyunca alarm çalar. Alarm bittiğinde ise ekrana uyarı mesajı verilir.

## Geliştirme Adımları 

Öncelikle activity_main.xml dosyasına 3 adet yumurta resmi ve 3 adet butonumuzu yerleştirdik.

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
Yani; Button'ın onClick event'i tetiklendiğinde hangi fonksiyonun çalışacağını belirledik.
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
Tekrar fonksiyona dönecek olursak, yeni ekledigimiz activitye geçmek için intent tanımladık. Intent tanımlarken içine context ve activitye verdigimiz isim parametrelerini gönderiyoruz. Yeni ekrana geçiş yaparken veri göndermek istersek **intent.putExtra()** fonksiyonunu kullanabiliriz. Göndereceğimiz veriler intent içerisinde key-value şeklinde tutulur.
Fonksiyonumuzdan yeni eklediğimiz activitye gidecek verinin keyi time, valuesu 5'dir.
StartActivity içine oluşturdugumuz intenti vererek yeni ekrana geçmiş oluyoruz.

Yeni açtigımız activity için kodları inceleyelim.

```var time = intent.getIntExtra("time", 1)```
MainActivity2nin içindeki onCreate fonksiyonuna gittigimizde yukarıdaki satırı görecegiz.
Bir önceki ekrandan yolladığımız keyi "time" olarak belirlediğimiz valueyi getIntExtra() fonksiyonu ile okuyoruz.
Artık sayacımızın kaç dakika boyunca çalışacağını biliyoruz.

```
object : CountDownTimer((time * 60 * 1000).toLong(), 1000)
```
CountDownTimer 2 adet parametre alır. Birincisi geri sayımı kaçtan başlayacak, ikincisi geri sayım kaçar kaçar azalacak. Bu değerleri milisaniye cinsinden alır.

```
override fun onTick(millisUntilFinished: Long)
```
onTick fonksiyonu CountDownTimer sınıfına aittir. Yukarıdaki CountDownTimer tanımından yola çıkarsak, onTick fonksiyonumuz saniyede bir kez çalışacaktır. millisUntilFinished değişkeni sayesinde timerin bitmesine ne kadar süre kaldığını görebiliriz.

``` 
var currentMinute = millisUntilFinished / 60000
var currentSecond = (millisUntilFinished % 60000) / 1000
```
onTick fonksiyonunun içinde saniye ve dakikayı yukarıda görüldüğü gibi hesaplıyoruz.

> app > res > values 

klasörü içinde oluşturduğumuz renkleri TimerBackgrondColor fonksiyonu ile when() koşuluyla istenilen dakikalarda arka plan rengimizi belirliyoruz.

Bir diger CountDownTimera ait fonksiyon olan onFinish içinde ise geri sayımımız bitince ne olmasını istiyorsak onu yazıyoruz.
Geri sayım bitiminde alarm çalması için öncelikle mp3 uzantılı dosyamızı 
> app > res > raw 
> 
klasörü içine atıyoruz.
Sonrasında
```
val mediaplayer = MediaPlayer.create(this@MainActivity2, R.raw.alarmmp3)
mediaplayer.start()
```
MediaPlayer.create ile ilk parametrede içinde bulunan activity ekranını, ikinci parametrede yükledigimiz ses dosyasının yolunu yazıyoruz.
Oluşturulan mediaplayer değişkenini start() edip alarmımızı başlatıyoruz.
Şimdide başlayan alarmın kaç saniye boyunca çalışmasını belirlemek için kullanılan yöntemimizi inceleyelim.

```
runnable = object : Runnable
```
**Runnable** ile arka planda çalışan bir kronometre oluştururuz.

```
handler.postDelayed(this,1000)
```
postDelayed fonksiyonu 2 adet paremetre alır. Birincisi içinde bulunan activity, ikincisi kronometre kaçar kaçar artacak.

Son olarak alarmın durmasını istediğimiz saniyede bildirim ekranını nasıl oluşturuyoruz inceleyelim.

```
val alert = AlertDialog.Builder(this@MainActivity2)
alert.setTitle("Finish")
alert.setMessage("Restart the cooking")
```
Yukarıda kod bloğunda AlertDialog.builder ile oluşturduğumuz bildirim ekranının detaylarını görüyoruz.
Oluşturulan bildirim ekranındaki butonlar ile yapıcaklarımızı aşağıda inceleyelim.

```
alert.setPositiveButton("Yes") {dialog, which ->
  val intent = Intent(applicationContext, MainActivity::class.java)
   startActivity(intent)
}
```
alert.setPositiveButton("Yes") butonu ile yeniden bir pişirme başlatmak istendiği durumda intent ile ilk açılan ekranımıza gidiyoruz.
Yeniden pişirme istenmediği durumda ise "No" butonuna tıklanınca
```
finishAffinity()
```
yukarıdaki kod yardımı ile programımızı sonlandırıyoruz.

