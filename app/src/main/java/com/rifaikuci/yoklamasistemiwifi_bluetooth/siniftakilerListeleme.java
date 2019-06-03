package com.rifaikuci.yoklamasistemiwifi_bluetooth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class siniftakilerListeleme extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siniftakiler_listeleme);
        intent=getIntent();
        TextView textView;
        textView=(TextView) findViewById(R.id.textView);


        textView.setText(intent.getStringExtra("types"));
    }
}
