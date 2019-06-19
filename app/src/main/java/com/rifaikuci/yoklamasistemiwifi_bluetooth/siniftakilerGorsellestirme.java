package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import static com.rifaikuci.yoklamasistemiwifi_bluetooth.siniftakilerListeleme.adSoyad;
import static com.rifaikuci.yoklamasistemiwifi_bluetooth.siniftakilerListeleme.mOgrenciList;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
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
    Button bbtnYoklamaKaydet, btnYoklamaGonder;
    ArrayList<String> dersListesi;
    DatabaseHelper myDb;
    SpinnerDialog spinnerDialog;
    Cursor res;
    String dosyaIsmi;
    String tarih;
    String dersAdi;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siniftakiler_gorsellestirme);


        bbtnYoklamaKaydet = (Button) findViewById(R.id.bbtnYoklamaKaydet);
        btnYoklamaGonder = (Button) findViewById(R.id.btnYoklamaGonder);
        geriGorsellestirme = (TextView) findViewById(R.id.geriGorsellestirme);
        txtOgrenciMevcudu = (TextView) findViewById(R.id.txtOgrenciMevcudu);
        dersListesi = new ArrayList<>();
        myDb = new DatabaseHelper(this);
        viewData();
       resimIslemleri();
        bbtnYoklamaKaydet.setVisibility(View.INVISIBLE);
        btnYoklamaGonder.setVisibility(View.INVISIBLE);

        for (ImageView image : imageArray) {
            image.setVisibility(View.INVISIBLE);
        }

        gorsellestirme();


        txtOgrenciMevcudu.setText("Sınıf Mevcudu : " + numaralar.size());

        ogrenciBilgileriDialog();
        transparan();
        spinnerMenu();
        geri();
        yoklamaKaydet();
        yoklamaGonder();
    }

    //resimlerin atama ve id belirleme işlemleri

    private void resimIslemleri() {
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);
        image7 = (ImageView) findViewById(R.id.image7);
        image8 = (ImageView) findViewById(R.id.image8);
        image9 = (ImageView) findViewById(R.id.image9);
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
    }

    //üst tarafın transparan yapılması
    private void transparan() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    //Spinner searchable menu işlemleri bunu kullanırken gradle kütüphane indirilmesi gerek
    private void spinnerMenu() {
        spinnerDialog = new SpinnerDialog(siniftakilerGorsellestirme.this, dersListesi, "Ders Seçiniz");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {


                SimpleDateFormat bicim = new SimpleDateFormat("dd-M-yyyy");
                Date simdiTarih = new Date();
                tarih = bicim.format(simdiTarih);
                siniftakilerGorsellestirme.this.tarih = bicim.format(simdiTarih);
                dersAdi = s.toString().trim();
                siniftakilerGorsellestirme.this.dosyaIsmi = s.toString().trim() + "_" + siniftakilerGorsellestirme.this.tarih + ".xls";
                if (isStoragePermissionGranted() == true) {
                    saveExcelFile(getApplicationContext(), dosyaIsmi.replace(" ", "_"), ilkHarfBuyuk(s.toString().trim()));
                    Toast.makeText(getApplicationContext(), ilkHarfBuyuk(s.toString().trim()) + " Dersi yoklaması Kaydedildi.", Toast.LENGTH_SHORT).show();
                    bbtnYoklamaKaydet.setVisibility(View.INVISIBLE);
                    btnYoklamaGonder.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getApplicationContext(), " Dersin  yoklamasını kaydetmek için izin vermeniz gerekmektedir. .", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    //Öğrenci bilgileri Dialog
    private void ogrenciBilgileriDialog() {
        for (int i = 0; i < numaralar.size(); i++) {

            final int finalI = i;
            imageArray[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(siniftakilerGorsellestirme.this);
                    View mView = getLayoutInflater().inflate(R.layout.ogrenci_detay, null);
                    TextView adsoyad = (TextView) mView.findViewById(R.id.adsoyad);
                    ImageView resim = (ImageView) mView.findViewById(R.id.ogrencininresim);
                    TextView numara = (TextView) mView.findViewById(R.id.numara);
                    TextView mac = (TextView) mView.findViewById(R.id.mac);

                    adsoyad.setText(adSoyad.get(finalI).toString());
                    numara.setText(numaralar.get(finalI).toString());
                    mac.setText(mOgrenciList.get(finalI).getMACadresi());


                    mBuilder.setView(mView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                }
            });
        }
    }

    //yoklama gönderme işlemleri
    private void yoklamaGonder() {
        btnYoklamaGonder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("plain/text");
                    intent.putExtra("android.intent.extra.EMAIL", new String[]{""});
                    intent.putExtra("android.intent.extra.SUBJECT", siniftakilerGorsellestirme.this.dersAdi + " Dersinin " + siniftakilerGorsellestirme.this.tarih + " Tarihli Yoklaması");
                    intent.putExtra("android.intent.extra.STREAM", siniftakilerGorsellestirme.this.getFilesDir() + "/" + siniftakilerGorsellestirme.this.dosyaIsmi + ".xls");
                    intent.putExtra("android.intent.extra.TEXT", "Yoklama Dosyası Excel Formatında Ektedir. ");
                    File file = new File(Environment.getExternalStorageDirectory() + "/" + siniftakilerGorsellestirme.this.dosyaIsmi);
                    Uri uri = Uri.fromFile(file);
                    intent.putExtra("android.intent.extra.STREAM", uri);
                    siniftakilerGorsellestirme.this.startActivity(Intent.createChooser(intent, "Send Email"));
                    btnYoklamaGonder.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Yoklama Gönderiliyor...", Toast.LENGTH_SHORT).show();

                } catch (Exception var5) {
                    var5.printStackTrace();
                }
            }
        });

    }

    //yoklamayı kayıt işlemleri
    private void yoklamaKaydet() {

        bbtnYoklamaKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (res.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Kayıtlı Ders bulunmamaktadır.", Toast.LENGTH_SHORT).show();

                } else {
                    spinnerDialog.showSpinerDialog();
                }
                //  Toast.makeText(getApplicationContext(),mOgrenciList.get(position).getAdSoyad(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Dersleri spinner menüsüne çekme
    public void viewData() {
        //  dersListesi.clear();
        res = myDb.getAllData();
        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Kayıtlı Ders Bulunamadı", Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()) {
            dersListesi.add(res.getString(1));

        }

    }

    //geri butonu
    private void geri() {
        geriGorsellestirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Öğrenci resim olarak ekrana atama
    public void gorsellestirme() {

        final Handler handler = new Handler();


        handler.post(new Runnable() {
            @Override
            public void run() {
                imageArray[i].setVisibility(View.VISIBLE);
                i++;
                if (i < numaralar.size()) {
                    handler.postDelayed(this, 500);
                } else {
                    Toast.makeText(siniftakilerGorsellestirme.this, "Yoklama Görselleştirme İşlemi Tammalandı", Toast.LENGTH_SHORT).show();
                    bbtnYoklamaKaydet.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    //verileri Excel Kayıt bunu kullanırken  gradle dosyalarına kütüphane eklenmesi gerekecek ve poi ile ilgili jar dosyalarıda manuel olarak indirilip eklenmesi gerekecek
    //elle indirdiğimiz dosyalar libs klasöründe bulabilirsiniz. poi-3.12-android-a.jar , poi-ooxml-schemas-3.12-20150511-a.jar dosya isimleri
    private static boolean saveExcelFile(Context context, String fileName, String Dersin) {

        // check if available and not read only

        SimpleDateFormat bicim = new SimpleDateFormat("dd.M.yyyy");
        Date simdiTarih = new Date();


        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            return false;
        }
        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;
        //Cell style for header row
        Font headerFont;
        headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 18);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle dersadi = wb.createCellStyle();

        dersadi.setAlignment(CellStyle.ALIGN_RIGHT);
        dersadi.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        dersadi.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        dersadi.setFont(headerFont);

        CellStyle cs = wb.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
        cs.setAlignment(CellStyle.ALIGN_LEFT);

        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs.setFont(headerFont);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("Yoklama Sayfası");
        Row row = null;
        // Generate column headings


        row = sheet1.createRow(0);
        row.setHeight((short) 1000);


        c = row.createCell(0);
        c.setCellValue("Ders Adı : " + Dersin.toString());
        c.setCellStyle(cs);


        c = row.createCell(1);
        c.setCellValue("");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("Tarih: " + bicim.format(simdiTarih));
        c.setCellStyle(dersadi);


        Cell ikinciSutun = null;
        //Cell style for header row
        Font fontiki;
        fontiki = wb.createFont();
        fontiki.setBold(true);
        fontiki.setFontHeightInPoints((short) 14);

        fontiki.setColor(IndexedColors.BLACK.getIndex());


        CellStyle cs2 = wb.createCellStyle();
        cs2.setFillForegroundColor(HSSFColor.WHITE.index);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);


        cs2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs2.setFont(fontiki);
        //New Sheet


        // Generate column headings
        Row row2 = sheet1.createRow(1);
        row2.setHeight((short) 300);


        ikinciSutun = row2.createCell(0);
        ikinciSutun.setCellValue("#");


        ikinciSutun.setCellStyle(cs2);

        ikinciSutun = row2.createCell(1);
        ikinciSutun.setCellValue("Ad Soyad ");
        ikinciSutun.setCellStyle(cs2);

        ikinciSutun = row2.createCell(2);
        ikinciSutun.setCellValue("Öğrenci Numarası");
        ikinciSutun.setCellStyle(cs2);


        for (int i = 2; i < numaralar.size() + 2; i++) {

            Row row3 = sheet1.createRow(i);
            row3.setHeight((short) 300);


            ikinciSutun = row3.createCell(0);
            ikinciSutun.setCellValue(i - 1);


            ikinciSutun.setCellStyle(cs2);

            ikinciSutun = row3.createCell(1);
            ikinciSutun.setCellValue(adSoyad.get(i - 2).toString().trim());
            ikinciSutun.setCellStyle(cs2);

            ikinciSutun = row3.createCell(2);
            ikinciSutun.setCellValue(numaralar.get(i - 2).toString().trim());
            ikinciSutun.setCellStyle(cs2);

        }


        sheet1.setColumnWidth(0, (15 * 600));
        sheet1.setColumnWidth(1, (15 * 400));
        sheet1.setColumnWidth(2, (15 * 500));
        sheet1.setHorizontallyCenter(true);

        // Create a path where we will place our List of objects on external storage
        File file = new File(Environment.getExternalStorageDirectory() + "/" + fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }
        return success;
    }

    //Cihaz izinleri
    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    //Cihaz hafız
    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    //ilkHarfi büyük
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

    //İzinle Alınıp alınmadığı kontrol ediliyor.
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    //İzinler için gelen cevaplar kontrolü
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //resume tasks needing this permission
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}