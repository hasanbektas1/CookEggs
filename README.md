# CookEggs
CookEggs

Öncelikle xml kısmında 3 adet yumurta resmi ve 3 adet butonumuzu yerleştirdik
Yumurta resimlerine göre undercooked 5 Minute, mediumcooked 8 minute, overcooked 12 minute, belirledik
Daha sonra MainActivity kısmında kullanıcının hangi yumurtayı seçecegine göre tıklanınca ne olacagı olayları yazdık
Tıklanılan yumurta ile bir sonraki activity ekranına geçilir ve geçilirken anahtar kelime ile veri gönderilir
Sonra geçilen activity ekranında gelen anahtar kelimenin verisine göre geri sayımın kaçtan başlatılacagını belirliyoruz
CountDownTimer methodunu saniye olarak hesabımızı yapıyoruz
Daha sonra azalan dakikaya göre background degişik renklendirme yapıyoruz
Dakika bitiminde alarm çalmasını saglıyoruz ve runnable yöntemi ile arkada kullanıcıya göstermeden 15 saniye saydırıp alarmı durduruyoruz
Alarmı durdurduktan sonra alertDialog yöntemi ile kullanıcıya uyarı mesajı verip yeni bir pişirme başlatıp başlatmayacgını soruyorz
İstemiyorsa program sonlanıyor istiyorsa yeniden başlangıç ekranına dönüş yapıyoruz


