package com.example.tugasbesar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class activity_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        LinearLayout Menu1 = (LinearLayout) findViewById(R.id.Menu1);
        Menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_admin.this, activity_tanggal.class);
                startActivity(i);
            }
        });

        LinearLayout Menu2 = (LinearLayout) findViewById(R.id.Menu2);
        Menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_admin.this, activity_list.class);
                startActivity(i);
            }
        });

    }
}
