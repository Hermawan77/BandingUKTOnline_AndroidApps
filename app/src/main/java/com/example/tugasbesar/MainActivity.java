package com.example.tugasbesar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;
    String dateref;
    DatabaseReference DatabaseTanggal;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseTanggal = FirebaseDatabase.getInstance().getReference("tanggal");
        final String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        LinearLayout menu1 = (LinearLayout) findViewById(R.id.menu1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.equals(dateref)) {
                Intent i = new Intent(MainActivity.this, PengajuanActivity.class);
                startActivity(i);
                } else {
                Toast.makeText(MainActivity.this, "Maaf Pengajuan Banding Belum Dibuka", Toast.LENGTH_LONG).show();
                }
            }
        });

        LinearLayout menu2 = (LinearLayout) findViewById(R.id.menu2);
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InformasiActivity.class);
                startActivity(i);
            }
        });

        LinearLayout menu3 = (LinearLayout) findViewById(R.id.menu3);
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PersyaratanActivity.class);
                startActivity(i);
            }
        });

        LinearLayout menu4 = (LinearLayout) findViewById(R.id.menu4);
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StatusActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Intent a = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(a);
        }
    return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseTanggal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot tanggalsnapshot: dataSnapshot.getChildren()){
                    Tanggal tanggal = tanggalsnapshot.getValue(Tanggal.class);
                    dateref = tanggal.getTanggal1();
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}