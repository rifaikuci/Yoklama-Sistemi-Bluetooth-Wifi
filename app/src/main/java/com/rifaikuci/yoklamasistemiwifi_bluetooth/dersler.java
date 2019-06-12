package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dersler extends AppCompatActivity {


    TextView geri;
    TextInputLayout dersAdi;
    FloatingActionButton plusButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersler);
        plusButton =(FloatingActionButton) findViewById(R.id.dersEkle);
        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        geri =(TextView) findViewById(R.id.geriDersler);
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = (ListView) findViewById(R.id.derslerListe);

        // Initializing a new String Array
        List<String> dersListesi= new ArrayList<>();
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");
        dersListesi.add("Bilgisayar Mühendisliği");


        // Create a List from String Array elements


        // Create an ArrayAdapter from List
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, dersListesi){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

                return view;
            }
        };

        listView.setAdapter(arrayAdapter);


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(dersler.this);
                View mView = getLayoutInflater().inflate(R.layout.dersdialog, null);
                 dersAdi = (TextInputLayout) mView.findViewById(R.id.text_dersAdi);
                Button btnEkle=(Button) mView.findViewById(R.id.btnEkleDialog);
                Button btnDuzenle=(Button) mView.findViewById(R.id.btnDuzenleDialog);
                btnEkle.setVisibility(View.VISIBLE);
                btnEkle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (  !validateUsername()  ) {


                            return;
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"ekle",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                btnDuzenle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (  !validateUsername()  ) {

                            return;
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"duzenle",Toast.LENGTH_SHORT).show();

                        }
                    }
                });


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    private boolean validateUsername() {
        String dersleri = dersAdi.getEditText().getText().toString().trim();

        if (dersleri.isEmpty()) {
            dersAdi.setError("Ders Adı Boş Geçilemez!!!");
            return false;
        } else if (dersleri.length() > 20) {
            dersAdi.setError("Ders Adı 20 Karakterden fazla !!!");
            return false;
        } else {
            dersAdi.setError(null);
            return true;
        }
    }
}
