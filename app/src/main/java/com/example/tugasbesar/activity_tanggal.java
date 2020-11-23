package com.example.tugasbesar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_tanggal extends AppCompatActivity {

    TextView texttanggal;
    EditText tanggalawal;
    EditText tanggalakhir;
    Button ok;
    DatabaseReference DatabaseTanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanggal);

        texttanggal = (TextView) findViewById(R.id.text);
        tanggalawal = (EditText) findViewById(R.id.value1);
        tanggalakhir = (EditText) findViewById(R.id.value2);
        ok = (Button) findViewById(R.id.ok);
        DatabaseTanggal = FirebaseDatabase.getInstance().getReference("tanggal");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTanggal();
            }
        });
    }

    private void addTanggal() {
        String date1 = tanggalawal.getText().toString().trim();
        String date2 = tanggalakhir.getText().toString().trim();

        if (!TextUtils.isEmpty(date1) || !TextUtils.isEmpty( date2)){
            String id = DatabaseTanggal.push().getKey();

            Tanggal tanggal = new Tanggal(id, date1, date2);
            DatabaseTanggal.child(id).setValue(tanggal);

            Toast.makeText(activity_tanggal.this, "Tanggal added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity_tanggal.this, "Isi data terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }
}
