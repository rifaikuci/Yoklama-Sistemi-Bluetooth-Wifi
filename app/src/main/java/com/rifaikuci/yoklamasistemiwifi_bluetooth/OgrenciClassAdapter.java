package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class OgrenciClassAdapter extends BaseAdapter {

    private Context mContext;
    private List<OgrenciClass> mOgrenciList;

    public OgrenciClassAdapter(Context mContext, List<OgrenciClass> mOgrenciList) {
        this.mContext = mContext;
        this.mOgrenciList = mOgrenciList;
    }


    @Override
    public int getCount() {
        return mOgrenciList.size();
    }

    @Override
    public Object getItem(int position) {
        return mOgrenciList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.ogrencilistesi_layout,null);
        TextView txtOgrenciadSoyad =(TextView) v.findViewById(R.id.txtAdSoyad);
        TextView txtOgrencinumara=(TextView) v.findViewById(R.id.txtOgrenciNumarasi);
        TextView txtMac=(TextView) v.findViewById(R.id.txtMac);
        ImageView image=(ImageView) v.findViewById(R.id.ogrenciResim);
        image.setImageResource(R.drawable.ogrenci);

        txtOgrenciadSoyad.setText(mOgrenciList.get(position).getAdSoyad());
        txtOgrencinumara.setText(mOgrenciList.get(position).getOkulNumarasi());
        txtMac.setText(mOgrenciList.get(position).getMACadresi());



        return v ;
    }
}
