package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.app.AlertDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList sayfaSozleri;
    Random random;
    int sayi;
    TextView rastgeleCumle,dialogText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rastgeleCumle = (TextView) findViewById(R.id.rastgeleCumle);
        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        sayfaSozleri = new ArrayList();
        sayfaSozleri.add("Bir öğretmen bir nesil demektir.;Megnet Zurner");
        sayfaSozleri.add("En iyi öğretmen, ömür boyu öğrenci kalabilendir.;Anonim");
        sayfaSozleri.add("Okul hayata hazırlanış değil, hayatın kendisidir.;F. ChateIain");
        sayfaSozleri.add("Eğitim, insanın okulda öğrendiği her şeyi unuttuğunda arta kalandır.;Albert Einstein");
        sayfaSozleri.add("Okul hayatı bitince, hayat okulu başIar.;Mümin Sekman");
        sayfaSozleri.add("Eğitim kafayı değiştirmek demektir. Belleği doldurmak değil.;Mark Twain");
        sayfaSozleri.add("Bir okul açılırsa bir hapishane kapanır.;Ralph Waldo Emerson");
        sayfaSozleri.add("Okul eğitimin sonu değil başlangıcıdır.;Lord Prougham");
        sayfaSozleri.add("Ekmekten sonra eğitim, bir milletin en büyük ihtiyacı.;Paul Richer");
        sayfaSozleri.add("Okulda okuduklarıyla iktifa edenler, ancak mürebbiyelerle konuşabilen çocuklara benzer.;Voltaire");
        sayfaSozleri.add("Üniversiteler öğrencilerin hem yeteneklerini, hem de yeteneksizliklerini ortaya çıkarır.;Anton Çehov");
        sayfaSozleri.add("En iyi bildiğimiz şeyIer, okulda öğrenmediklerimizdir.;Luc de Olabices");
        sayfaSozleri.add("İnsanlar tek kitabın bile bir insanın hayatını değiştirmeye yetebileceğini bir türlü anlamıyor.;Malcolm X");
        sayfaSozleri.add("Akılsız adam taş gibi: Suya düşerse batar. Saf yürekli adam şeker gibi: Suya düşerse erir. Bilge kişi yağ gibi: Suya düşerse yüzer.;İsmet Özel");
        sayfaSozleri.add("Konuşmak, sürekli konuşmak… Sonra kalkıp bir şey yapmamak… Çoğu zaman içine düştüğümüz abes durumlardan biridir bu.;Seyyid Kutup");
        sayfaSozleri.add("Okuyun. zira mürekkebin akmadığı yerde kan akıyor!.;Ali Şeriati");
        sayfaSozleri.add("Bir ülkenin geleceği, o ülke insanlarının göreceği eğitime bağlıdır.;Albert Einstein");
        sayfaSozleri.add("Eğitim, meyvenin kendisi değil, bilgi ağacından meyve toplamaya yarayan bir merdivendir.;Bernard Shaw");
        sayfaSozleri.add("Eğitimin temel amacı, çocukları kendi yeteneklerinin bilincine vardırmaktır.;Erich Fromm");
        sayfaSozleri.add("Eğitimin asıl büyük amacı, bilgilenmek değil, eyleme geçmektir.;Herbert Spencer");
        sayfaSozleri.add("Bilginin gücüne inanıyorum, kültürün gücüne inanıyorum; ama eğitimin gücüne, daha çok inanıyorum.;Seyyid Kutup");

        random = new Random();

        sayi = random.nextInt(sayfaSozleri.size());
        String metin = (String) sayfaSozleri.get(sayi);
        final String[] separated  =metin.split(";");
        rastgeleCumle.setText(separated[0]);


        rastgeleCumle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),separated[1],Toast.LENGTH_SHORT).show();
                CustomMessageBox();
            }
        });


    }



    protected    void  CustomMessageBox()
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_messbox, null);
        TextView yazar = (TextView) mView.findViewById(R.id.yazar);
        ImageView resim = (ImageView) mView.findViewById(R.id.resim);
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
        //eklendi
    }
}
