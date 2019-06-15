package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

import static com.rifaikuci.yoklamasistemiwifi_bluetooth.siniftakilerListeleme.numaralar;

public class siniftakilerGorsellestirme extends AppCompatActivity {


    TextView geriGorsellestirme, txtOgrenciMevcudu;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;
    ImageView image11, image12, image13, image14, image15, image16, image17, image18, image19, image20;
    ImageView image21, image22, image23, image24, image25, image26, image27, image28, image29, image30;
    ImageView image31, image32, image33, image34, image35, image36, image37, image38, image39, image40;
    ImageView image41, image42, image43, image44, image45, image46, image47, image48;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    Button bbtnYoklamaKaydet,btnYoklamaGonder;
    ArrayList<String> dersListesi;
    DatabaseHelper myDb;
    SpinnerDialog spinnerDialog;
    Cursor res;



    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siniftakiler_gorsellestirme);


        bbtnYoklamaKaydet    = (Button)findViewById(R.id.bbtnYoklamaKaydet);
        btnYoklamaGonder    = (Button)findViewById(R.id.btnYoklamaGonder);
        geriGorsellestirme = (TextView) findViewById(R.id.geriGorsellestirme);
        txtOgrenciMevcudu = (TextView) findViewById(R.id.txtOgrenciMevcudu);
        dersListesi= new ArrayList<>();
        myDb = new DatabaseHelper(this);
        viewData();
            image1  = (ImageView) findViewById(R.id.image1);
            image2  = (ImageView) findViewById(R.id.image2);
            image3  = (ImageView) findViewById(R.id.image3);
            image4  = (ImageView) findViewById(R.id.image4);
            image5  = (ImageView) findViewById(R.id.image5);
            image6  = (ImageView) findViewById(R.id.image6);
            image7  = (ImageView) findViewById(R.id.image7);
            image8  = (ImageView) findViewById(R.id.image8);
            image9  = (ImageView) findViewById(R.id.image9);
            image10 = (ImageView) findViewById(R.id.image10);
            image11 = (ImageView) findViewById(R.id.image11);
            image12 = (ImageView) findViewById(R.id.image12);
            image13 = (ImageView) findViewById(R.id.image13);
            image14 = (ImageView) findViewById(R.id.image14);
            image15 = (ImageView) findViewById(R.id.image15);
            image16 = (ImageView) findViewById(R.id.image16);
            image17 = (ImageView) findViewById(R.id.image17);
            image18 = (ImageView) findViewById(R.id.image18);
            image19 = (ImageView) findViewById(R.id.image19);
            image20 = (ImageView) findViewById(R.id.image20);
            image21 = (ImageView) findViewById(R.id.image21);
            image22 = (ImageView) findViewById(R.id.image22);
            image23 = (ImageView) findViewById(R.id.image23);
            image24 = (ImageView) findViewById(R.id.image24);
            image25 = (ImageView) findViewById(R.id.image25);
            image26 = (ImageView) findViewById(R.id.image26);
            image27 = (ImageView) findViewById(R.id.image27);
            image28 = (ImageView) findViewById(R.id.image28);
            image29 = (ImageView) findViewById(R.id.image29);
            image30 = (ImageView) findViewById(R.id.image30);
            image31 = (ImageView) findViewById(R.id.image31);
            image32 = (ImageView) findViewById(R.id.image32);
            image33 = (ImageView) findViewById(R.id.image33);
            image34 = (ImageView) findViewById(R.id.image34);
            image35 = (ImageView) findViewById(R.id.image35);
            image36 = (ImageView) findViewById(R.id.image36);
            image37 = (ImageView) findViewById(R.id.image37);
            image38 = (ImageView) findViewById(R.id.image38);
            image39 = (ImageView) findViewById(R.id.image39);
            image40 = (ImageView) findViewById(R.id.image40);
            image41 = (ImageView) findViewById(R.id.image41);
            image42 = (ImageView) findViewById(R.id.image42);
            image43 = (ImageView) findViewById(R.id.image43);
            image44 = (ImageView) findViewById(R.id.image44);
            image45 = (ImageView) findViewById(R.id.image45);
            image46 = (ImageView) findViewById(R.id.image46);
            image47 = (ImageView) findViewById(R.id.image47);
            image48 = (ImageView) findViewById(R.id.image48);
        imageArray = new ImageView[]{image1, image2, image3, image4, image5, image6, image7, image8, image9, image10,
                image11, image12, image13, image14, image15, image16, image17, image18, image19, image20,
                image21, image22, image23, image24, image25, image26, image27, image28, image29, image30,
                image31, image32, image33, image34, image35, image36, image37, image38, image39, image40,
                image41, image42, image43, image44, image45, image46, image47, image48
        };
        bbtnYoklamaKaydet.setVisibility(View.INVISIBLE);
        btnYoklamaGonder.setVisibility(View.INVISIBLE);

        for (ImageView image : imageArray) {
            image.setVisibility(View.INVISIBLE);
        }

        gorsellestirme();


        txtOgrenciMevcudu.setText("Sınıf Mevcudu : " + numaralar.size());


        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        spinnerDialog = new SpinnerDialog(siniftakilerGorsellestirme.this,dersListesi,"Ders Seçiniz");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                Toast.makeText(getApplicationContext(),"Seçilen Ders"+s.toString().trim(),Toast.LENGTH_SHORT).show();
            }
        });
        geri();
        yoklamaKaydet();
    }

    private void yoklamaKaydet() {

    bbtnYoklamaKaydet.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(res.getCount()==0)
            {
                Toast.makeText(getApplicationContext(),"Kayıtlı Ders bulunmamaktadır.",Toast.LENGTH_SHORT).show();

            }
            else {
                spinnerDialog.showSpinerDialog();
            }
            //  Toast.makeText(getApplicationContext(),mOgrenciList.get(position).getAdSoyad(),Toast.LENGTH_SHORT).show();
        }
    });}

    public  void viewData()
    {
        //  dersListesi.clear();
         res =  myDb.getAllData();
        if(res.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"Kayıtlı Ders Bulunamadı",Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext())
        {
            dersListesi.add(res.getString(1));

        }

    }
    private void geri() {
        geriGorsellestirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void gorsellestirme() {

        final Handler handler = new Handler();


        handler.post(new Runnable() {
            @Override
            public void run() {
               imageArray[i].setVisibility(View.VISIBLE);
                i++;
                if (i <numaralar.size()) {
                    handler.postDelayed(this, 500);
                }
                else
                {
                    Toast.makeText(siniftakilerGorsellestirme.this, "Yoklama Görselleştirme İşlemi Tammalandı", Toast.LENGTH_SHORT).show();
                bbtnYoklamaKaydet.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
