package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dersler extends AppCompatActivity {


    TextView geri;
    TextInputLayout dersAdi;
    FloatingActionButton plusButton;
    DatabaseHelper myDb;
    TextInputEditText dersInput;
    List<String> dersListesi, listeID;
    Button btnEkle,btnDuzenle;
    ListView listView;

    AlertDialog.Builder mBuilder;
    View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersler);
        dersInput = (TextInputEditText) findViewById(R.id.dersInput);
        listView = (ListView) findViewById(R.id.derslerListe);
        plusButton =(FloatingActionButton) findViewById(R.id.dersEkle);



        dersListesi= new ArrayList<>();
        listeID = new ArrayList<>();
        myDb = new DatabaseHelper(this);
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

        viewData(); // verileri Listview'e atama


        //verileri listeye gösterme
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

        // ders ekleme
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(dersler.this);
                View mView = getLayoutInflater().inflate(R.layout.dersdialog, null);
                 dersAdi = (TextInputLayout) mView.findViewById(R.id.text_dersAdi);
                 btnEkle=(Button) mView.findViewById(R.id.btnEkleDialog);
                 btnDuzenle=(Button) mView.findViewById(R.id.btnDuzenleDialog);
                 btnDuzenle.setVisibility(View.INVISIBLE);
                 btnEkle.setVisibility(View.VISIBLE);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                btnEkle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (  !validateUsername()  ) {
                            return;
                        }

                        else {
                            boolean isInserted =myDb.insertData(ilkHarfBuyuk(dersAdi.getEditText().getText().toString().trim()));
                            if(isInserted==true)
                            {
                                Toast.makeText(getApplicationContext(),ilkHarfBuyuk(dersAdi.getEditText().getText().toString().trim())+
                                        " Dersi Eklendi",Toast.LENGTH_LONG).show();
                                dersAdi.getEditText().setText("");
                                finish();
                                startActivity(getIntent());
                                dialog.closeOptionsMenu();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),ilkHarfBuyuk(dersAdi.getEditText().getText().toString().trim())+
                                        " Dersi Eklenirken bir hata oluştu !",Toast.LENGTH_SHORT).show();
                            }} }});
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(dersler.this);
                View mView = getLayoutInflater().inflate(R.layout.dersdialog, null);
                dersAdi = (TextInputLayout) mView.findViewById(R.id.text_dersAdi);
                btnEkle=(Button) mView.findViewById(R.id.btnEkleDialog);
                btnDuzenle=(Button) mView.findViewById(R.id.btnDuzenleDialog);



                btnDuzenle.setVisibility(View.VISIBLE);
                btnEkle.setVisibility(View.INVISIBLE);
                dersAdi.getEditText().setText(dersListesi.get(position));

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnDuzenle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (  !validateUsername()  ) {

                            return;
                        }
                        else
                        {
                            boolean isUpdate =myDb.updateData(listeID.get(position),ilkHarfBuyuk(dersAdi.getEditText().getText().toString().trim()));
                            if(isUpdate==true)
                            {
                                Toast.makeText(getApplicationContext(),"Ders Adı Başarılı bir şekilde güncellendi",Toast.LENGTH_SHORT).show();
                                dialog.closeOptionsMenu();
                                finish();
                                startActivity(getIntent());
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Ders Adını güncellerken bir hata oluştu !",Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                });
            }
        });

        dersSilmeIslemi();
    }
        //onLongItem click ile ders silme işlemi
    private void dersSilmeIslemi() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dersler.this);
                builder.setTitle("Uyarı");
                builder.setMessage(dersListesi.get(position)+" Dersini Silmek İstediğinizden Emin misiniz? ");

                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        Integer deleteRows =myDb.deleteData(listeID.get(position));
                        if(deleteRows>0) {
                            Toast.makeText(getApplicationContext(), dersListesi.get(position) + " Dersi Silindi", Toast.LENGTH_SHORT).show();
                            listeID.remove(position);
                            dersListesi.remove(position);
                            finish();
                            startActivity(getIntent());

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), dersListesi.get(position) + " Dersi Silinemedi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        Toast.makeText(getApplicationContext()," Ders  silme işlemi iptal edildi",Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alert = builder.create();

                alert.show();
                return true;
            }
        });
    }

    //dersleri sqlite çağrıldı.
    public  void viewData()
    {
     //  dersListesi.clear();
        Cursor res =  myDb.getAllData();
        if(res.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"Kayıtlı Ders Bulunamadı",Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext())
        {
            listeID.add(res.getString(0));
            dersListesi.add(res.getString(1));

        }

    }

    //TextInput kontrolleri yapıldı
    private boolean validateUsername() {
        String dersleri = dersAdi.getEditText().getText().toString().trim();

        if (dersleri.isEmpty()) {
            dersAdi.setError("Ders Adı Boş Geçilemez!!!");
            return false;
        } else if (dersleri.length() > 40) {
            dersAdi.setError("Ders Adı 40 Karakterden fazla !!!");
            return false;
        } else {
            dersAdi.setError(null);
            return true;
        }
    }

    String ilkHarfBuyuk(String str) {
        // str Stringinin içindeki kelimelerin ilk harfleri büyük diğerleri küçük yapılır.
        char c = Character.toUpperCase(str.charAt(0));
        //ilk harfini buyuttuk
        str = c + str.substring(1);
        //buyutulen ilk harften sonra kelimenin diger harflerini ekledik.
        String bosluk = " ";
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                c = Character.toUpperCase(str.charAt(i + 1));
                str = str.substring(0, i) + bosluk + c + str.substring(i + 2);

            }

        }
        return str;
    }
}
