package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class siniftakilerListeleme extends AppCompatActivity {
    Intent intent;
    private ListView ogrencilerList;
    private OgrenciClassAdapter adapter;
    private List<OgrenciClass> mOgrenciList;
    private  ArrayList<String> numaralar;
    private  ArrayList<String> adSoyad;
    TextView geri;
    Button btnOgrenciEkle,btnDevam;
    BluetoothAdapter mBluetoothAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siniftakiler_listeleme);

        mBluetoothAdapter =BluetoothAdapter.getDefaultAdapter();
        intent=getIntent();
        String gelenDeger =intent.getStringExtra("types");

        geri            = (TextView) findViewById(R.id.deneme);
        ogrencilerList  = (ListView) findViewById(R.id.liste);
        btnDevam        = (Button)findViewById(R.id.btnDevam);
        btnOgrenciEkle  = (Button)findViewById(R.id.btnOgrenciEkle);

        mOgrenciList    = new ArrayList<>();
        numaralar       = new ArrayList<>();
        adSoyad         = new ArrayList<>();



        if(gelenDeger.equalsIgnoreCase("wifi"))
        {
            Toast.makeText(getApplicationContext(),"Wifi Seçildi",Toast.LENGTH_LONG).show();
            mOgrenciList.add(new OgrenciClass("wifi Ferhat Özdemir","1611404471","02:00:00:00:00:00"));

        }

        // Eğer bluetooth seçilirse
        if(gelenDeger.equalsIgnoreCase("bluetooth"))
        {

            //çevredeki cihazları fonksiyon
            availableDevice();

            //Kendi cihazımızın ismini alındı.
            getMyDeviceName();
        }




        if(Build.VERSION.SDK_INT>=19)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //textView.setText(intent.getStringExtra("types"));



        ogrencilerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(siniftakilerListeleme.this);
            View mView = getLayoutInflater().inflate(R.layout.ogrenci_detay, null);
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
    geri();//geri tuşu

    }




    //kendi cihazımızı getirme fonksiyonu
    private void getMyDeviceName() {
        try {
            BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();

            String[] bol = myDevice.getName().split("-");

            if (bol[1].trim().length() == 10 && TextUtils.isDigitsOnly(bol[1].trim())) {

                if(!numaralar.contains(bol[1].toString().trim())) {
                    mOgrenciList.add(new OgrenciClass(bol[0].trim(),bol[1].trim(),myDevice.getAddress()));
                    adSoyad.add(bol[0].toString().trim());
                    numaralar.add(bol[1].toString().trim());
                }

                adapter = new OgrenciClassAdapter(getApplicationContext(), mOgrenciList);
                ogrencilerList.setAdapter(adapter);
            }

        } catch (Exception var7) {}

    }

    //Çevredeki cihazları tarama izinleri
    private void availableDevice() {

        if(mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();

            //check BT permissions in manifest
            checkBTPermissions();
            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
        if(!mBluetoothAdapter.isDiscovering()){
            //check BT permissions in manifest
            checkBTPermissions();
            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
    }

    // Bluetooth Sdk Kontrolü
    private void checkBTPermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            }
            if (permissionCheck != 0) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
                }
            }
        }else{
            Toast.makeText(getApplicationContext(),"Telefonuz Bluetooth Desteklenmemektedir.",Toast.LENGTH_SHORT).show();

        }
    }
    // geri buton fonksiyonu
    public  void geri()
    {
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Çevredeki Cihazları Tarama
    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                try {
                    String[] separated = device.getName().split("-");
                    separated[1] = separated[1].trim();
                    if (separated[1].length() == 10 && TextUtils.isDigitsOnly(separated[1])) {

                        if (!numaralar.contains(separated[1].toString().trim())) {
                            mOgrenciList.add(new OgrenciClass(separated[0].toString().trim(), separated[1].toString().trim(), device.getAddress()));
                            adSoyad.add(separated[0].toString().trim());
                            numaralar.add(separated[1].toString().trim());
                        }
                    }
                    adapter = new OgrenciClassAdapter(getApplicationContext(), mOgrenciList);
                    ogrencilerList.setAdapter(adapter);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    };
}