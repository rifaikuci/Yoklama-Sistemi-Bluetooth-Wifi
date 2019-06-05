package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class siniftakilerListeleme extends AppCompatActivity {
    Intent intent;
    private ListView ogrencilerList;
    private OgrenciClassAdapter adapter;
    private List<OgrenciClass> mOgrenciList;
    TextView geri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siniftakiler_listeleme);
        intent=getIntent();
        geri = (TextView) findViewById(R.id.deneme);

        ogrencilerList =(ListView) findViewById(R.id.liste);

        mOgrenciList = new ArrayList<>();

        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //textView.setText(intent.getStringExtra("types"));

        mOgrenciList.add(new OgrenciClass("Rifai Kuçi","1611404401","02:00:00:00:00:00"));
        mOgrenciList.add(new OgrenciClass("Ömer Faruk Eskicioğlu","1511404404","02:00:0E:00:00:00"));
        mOgrenciList.add(new OgrenciClass("Çağrı Gonca","1611447401","02:00:00:00:FG:0F"));
        mOgrenciList.add(new OgrenciClass("İbrahim Ferhat Özdemir","1611404471","02:00:00:00:00:00"));

    adapter = new OgrenciClassAdapter(getApplicationContext(),mOgrenciList);
    ogrencilerList.setAdapter(adapter);

    ogrencilerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(siniftakilerListeleme.this);
            View mView = getLayoutInflater().inflate(R.layout.ogrenciDetay, null);
            TextView adsoyad= (TextView) mView.findViewById(R.id.adsoyad);
            ImageView resim = (ImageView) mView.findViewById(R.id.ogrencininresim);
            TextView numara =(TextView) mView.findViewById(R.id.numara);
            TextView mac =(TextView) mView.findViewById(R.id.mac);

           adsoyad.setText(mOgrenciList.get(position).getAdSoyad());
           numara.setText(mOgrenciList.get(position).getOkulNumarasi());
           mac.setText(mOgrenciList.get(position).getMACadresi());


            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
            Toast.makeText(getApplicationContext(),mOgrenciList.get(position).getAdSoyad(),Toast.LENGTH_SHORT).show();
        }
    });

    geri.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    });
    }
}
