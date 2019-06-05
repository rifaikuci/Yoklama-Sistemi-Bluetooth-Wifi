package com.rifaikuci.yoklamasistemiwifi_bluetooth;

public class OgrenciClass {


    String adSoyad,okulNumarasi,MACadresi;

    public OgrenciClass(String adSoyad, String okulNumarasi, String MACadresi) {
        this.adSoyad = adSoyad;
        this.okulNumarasi = okulNumarasi;
        this.MACadresi = MACadresi;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getOkulNumarasi() {
        return okulNumarasi;
    }

    public void setOkulNumarasi(String okulNumarasi) {
        this.okulNumarasi = okulNumarasi;
    }

    public String getMACadresi() {
        return MACadresi;
    }

    public void setMACadresi(String MACadresi) {
        this.MACadresi = MACadresi;
    }
}
