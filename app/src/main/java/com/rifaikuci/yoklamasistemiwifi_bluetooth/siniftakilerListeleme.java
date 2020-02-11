package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class siniftakilerListeleme extends AppCompatActivity {
    Intent intent;
    private  ListView ogrencilerList;
    private  OgrenciClassAdapter adapter;
    public static   List<OgrenciClass> mOgrenciList = new ArrayList<>();
    public static ArrayList<String> numaralar = new ArrayList<>();
    public static ArrayList<String> adSoyad = new ArrayList<>();


    TextView geri;
    Button btnOgrenciEkle,btnDevam,btnEkleOgrenci;
    BluetoothAdapter mBluetoothAdapter;
    TextInputLayout ekleOgrenciadSoyad,ekleOgrenciNumara;
    TextInputEditText ediTextAdSoyad,editTextNumara;



    private WifiManager wifiManager;
    private ListView listView;
    private Button buttonScan;
    private int size = 0;
    private List<ScanResult> results;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter adapters;






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






        if(gelenDeger.equalsIgnoreCase("wifi"))
        {
            wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            if (!wifiManager.isWifiEnabled()) {
                Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();
                wifiManager.setWifiEnabled(true);
            }
sdsd
            scanWifi();
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
    ogrenciEkle();
    devam();
    manuelOgrenciSilme();

    }

    private void manuelOgrenciSilme() {
    ogrencilerList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

            AlertDialog.Builder builder = new AlertDialog.Builder(siniftakilerListeleme.this);
            builder.setTitle("Uyarı");
            builder.setMessage(adSoyad.get(position)+" Öğrencisini listeden silmek istediğinizden emin misiniz?  ");

            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    numaralar.remove(position);
                    adSoyad.remove(position);
                    mOgrenciList.remove(position);

                    finish();
                        startActivity(getIntent());




                }
            });

            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    Toast.makeText(getApplicationContext()," Öğrenci silme işlemi iptal edildi",Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alert = builder.create();

            alert.show();
            return true;
        }
    });
    }

    private void devam() {
    btnDevam.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(),siniftakilerGorsellestirme.class);
            startActivity(intent);
        }
    });
    }


    //Manuel Olarak Öğrenci Ekleme
    private void ogrenciEkle() {
        btnOgrenciEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(siniftakilerListeleme.this);
                View mView = getLayoutInflater().inflate(R.layout.manuel_ogrenci_ekle, null);
                ekleOgrenciadSoyad = (TextInputLayout) mView.findViewById(R.id.adSoyadi);
                ekleOgrenciNumara  = (TextInputLayout) mView.findViewById(R.id.numarasi);
                ediTextAdSoyad     =  (TextInputEditText) mView.findViewById(R.id.ekleOgrenciadSoyad);
                editTextNumara     =  (TextInputEditText) mView.findViewById(R.id.ekleOgrenciNumara);
                btnEkleOgrenci=(Button) mView.findViewById(R.id.btnEkleOgrenci);


                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                btnEkleOgrenci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (  !validateUsername()  ) {
                            return;
                        }
                        else {


                            if(ekleOgrenciNumara.getEditText().getText().toString().trim().length()==10 && ekleOgrenciadSoyad.getEditText().getText().toString().isEmpty()==false)
                            {
                                if(!numaralar.contains(ekleOgrenciNumara.getEditText().getText().toString())) {
                                    mOgrenciList.add(new OgrenciClass(ilkHarfBuyuk(ekleOgrenciadSoyad.getEditText().getText().toString().trim()), ekleOgrenciNumara.getEditText().getText().toString()));
                                    Toast.makeText(getApplicationContext(), ilkHarfBuyuk(ekleOgrenciadSoyad.getEditText().getText().toString().trim()) + " Sınıf Listesine Eklendi", Toast.LENGTH_SHORT).show();
                                    numaralar.add(ekleOgrenciNumara.getEditText().getText().toString());
                                    adSoyad.add(ilkHarfBuyuk(ekleOgrenciadSoyad.getEditText().getText().toString().trim()));

                                    ekleOgrenciadSoyad.getEditText().setText("");
                                    ekleOgrenciNumara.getEditText().setText("");
                                    adapter = new OgrenciClassAdapter(getApplicationContext(), mOgrenciList);
                                    ogrencilerList.setAdapter(adapter);
                                    dialog.dismiss();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Aynı numarada sınıfta öğrenci bulunmaktadır.",Toast.LENGTH_SHORT).show();

                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Öğrenci Bilgileri Hatalı girilmiştir",Toast.LENGTH_SHORT).show();

                            }

                        }


                    }});
            }
        });

    }
    //Öğrenci numarası kontrolleri yapılıyor
    private boolean validateUsername() {
        String dersleri = ekleOgrenciNumara.getEditText().getText().toString().trim();

        if (dersleri.isEmpty()) {
            ekleOgrenciNumara.setError("Öğrenci Numarası Boş Geçilemez!!!");
            return false;
        } else if (dersleri.length() ==11) {
            ekleOgrenciNumara.setError("Öğrenci Numarası 10 haneli olmalı !!!");
            return false;
        } else {
            ekleOgrenciNumara.setError(null);
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

    //kendi cihazımızı getirme fonksiyonu
    private void getMyDeviceName() {
        try {
            BluetoothAdapter myDevice = BluetoothAdapter.getDefaultAdapter();

            String[] bol = myDevice.getName().split("-");

            if (bol[1].trim().length() == 10 && TextUtils.isDigitsOnly(bol[1].trim())) {

                if(!numaralar.contains(bol[1].toString().trim())) {
                    mOgrenciList.add(new OgrenciClass(ilkHarfBuyuk(bol[0].trim()),bol[1].trim(),myDevice.getAddress()));
                    adSoyad.add(ilkHarfBuyuk(bol[0].toString().trim()));
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
                            mOgrenciList.add(new OgrenciClass(ilkHarfBuyuk(separated[0].toString().trim()), separated[1].toString().trim(), device.getAddress()));
                            adSoyad.add(ilkHarfBuyuk(separated[0].toString().trim()));
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

    private void scanWifi() {
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();
            unregisterReceiver(this);

            for (ScanResult scanResult : results) {

                arrayList.add(scanResult.SSID + " - " + scanResult.capabilities);

                try {
                    String[] separated = scanResult.SSID.split("-");
                    separated[1] = separated[1].trim();
                    if (separated[1].length() == 10 && TextUtils.isDigitsOnly(separated[1])) {

                        if (!numaralar.contains(separated[1].toString().trim())) {
                            mOgrenciList.add(new OgrenciClass(ilkHarfBuyuk(separated[0].toString().trim()), separated[1].toString().trim(), scanResult.BSSID));
                            adSoyad.add(ilkHarfBuyuk(separated[0].toString().trim()));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}