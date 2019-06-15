package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList sayfaSozleri;
    Random random;
    int sayi;
    TextView rastgeleCumle,dialogText,txtTarih;
    int[] imageArray;
    int image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15,image16;
    int image17,image18,image19,image20,image21;
    SwipeButton swipeWifi,swipeBluetooth,swipeKapat,swipeDersler;
    WifiManager wifiManager;
    BluetoothAdapter bluetoothAdapter;
    Intent intent;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rastgeleCumle = (TextView) findViewById(R.id.rastgeleCumle);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        txtTarih =(TextView) findViewById(R.id.txtTarih);
        SimpleDateFormat bicim = new SimpleDateFormat("dd/M/yyyy");
        Date simdiTarih = new Date();


        txtTarih.setText(bicim.format(simdiTarih));

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

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
        sayfaSozleri.add("Okul hayatı bitince, hayat okulu başlar.;Mümin Sekman");
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
        sayfaSozleri.add("Bilginin gücüne inanıyorum, kültürün gücüne inanıyorum ama eğitimin gücüne, daha çok inanıyorum.;Seyyid Kutup");

        image1  = R.drawable.anonim;
        image2  = R.drawable.anonim;
        image3  = R.drawable.anonim;
        image4  = R.drawable.alberteinstein;
        image5  = R.drawable.muminsekman;
        image6  = R.drawable.mark;
        image7  = R.drawable.ralph;
        image8  = R.drawable.lord;
        image9  = R.drawable.paul;
        image10 = R.drawable.voltaire;
        image11 = R.drawable.cehob;
        image12 = R.drawable.anonim;
        image13 = R.drawable.malcolm;
        image14 = R.drawable.ismet;
        image15 = R.drawable.seyyit;
        image16 = R.drawable.seriati;
        image17 = R.drawable.alberteinstein;
        image18 = R.drawable.bernardshaw;
        image19 = R.drawable.erich;
        image20 = R.drawable.herber;
        image21 = R.drawable.seyyit;
        imageArray =new int[] {image1,image2,image3,image4,image5,image6,
                image7,image8,image9,image10,image11,image12,image13,image14,image15,image16,image17,image18,image19,image20,image21};


        random = new Random();

        sayi = random.nextInt(sayfaSozleri.size());
        String metin = (String) sayfaSozleri.get(sayi);
        final String[] separated  =metin.split(";");
        rastgeleCumle.setText(separated[0]);






        rastgeleCumle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_messbox, null);
                TextView yazar = (TextView) mView.findViewById(R.id.yazar);
                ImageView resim = (ImageView) mView.findViewById(R.id.resim);
                yazar.setText(separated[1].toString());
                resim.setImageResource(imageArray[sayi]);


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }
        });

        swipeKapat = (SwipeButton) findViewById(R.id.swipe_kapat);
        swipeBluetooth = (SwipeButton) findViewById(R.id.swipe_bluetooth);
        swipeWifi = (SwipeButton) findViewById(R.id.swipe_wifi);
        swipeDersler=(SwipeButton) findViewById(R.id.swipe_dersler);

        swipeKapat.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {


                try {
                    Thread.sleep(2000);
                    Toast.makeText(MainActivity.this, "Uygulama Kapatıldı.", Toast.LENGTH_SHORT).show();
                    finish();
                    moveTaskToBack(true);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        swipeBluetooth.setOnStateChangeListener(new OnStateChangeListener() {
            @SuppressLint("NewApi")
            @Override
            public void onStateChange(boolean active) {

                if(bluetoothAdapter==null)
                {
                    Toast.makeText(getApplicationContext(),"Telefonuz Bluetooth Desteklenmemektedir.",Toast.LENGTH_SHORT).show();
                    startActivity(getIntent());
                }
                else {
                    if (bluetoothAdapter.isEnabled() == false) {
                        Toast.makeText(MainActivity.this, "Bluetooth ile yoklama alınabilmesi için Bluetooth açılması gerekmektedir.", Toast.LENGTH_SHORT).show();
                        bluetoothAdapter.enable();


                        Intent intent = new Intent(getApplicationContext(), siniftakilerListeleme.class);
                        intent.putExtra("types", "bluetooth");
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Bluetooth Açık", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), siniftakilerListeleme.class);
                        intent.putExtra("types", "bluetooth");
                        startActivity(intent);
                    }
                }

            }
        });



        swipeWifi.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

                if(wifiManager.isWifiEnabled()==false)
                {
                    Toast.makeText(MainActivity.this, "Wifi üzerinden yoklama almak için wifi ağınızı açılacaktır.", Toast.LENGTH_SHORT).show();
                    wifiManager.setWifiEnabled(true);


                    Intent intent = new Intent(getApplicationContext(),siniftakilerListeleme.class);
                    intent.putExtra("types","wifi");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "wifi Ağınız Açık", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),siniftakilerListeleme.class);
                    intent.putExtra("types","wifi");
                    startActivity(intent);
                }



            }
        });

        swipeDersler.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Intent intent = new Intent(getApplicationContext(),dersler.class);
                startActivity(intent);
            }
        });


    }


}

