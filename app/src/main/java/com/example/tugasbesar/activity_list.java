package com.example.tugasbesar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_list extends AppCompatActivity {

    ListView listViewMahasiswa;
    DatabaseReference databaseMahasiswa;

    List<Mahasiswa> mahasiswaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        databaseMahasiswa = FirebaseDatabase.getInstance().getReference("Mahasiswa");

        listViewMahasiswa = (ListView) findViewById(R.id.listViewMahasiswa);
        mahasiswaList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseMahasiswa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mahasiswaList.clear();

                for (DataSnapshot mahasiswaSnapshot: dataSnapshot.getChildren()){
                    Mahasiswa mahasiswa = mahasiswaSnapshot.getValue(Mahasiswa.class);

                    mahasiswaList.add(mahasiswa);
                }
                list_item adapter = new list_item(activity_list.this, mahasiswaList);
                listViewMahasiswa.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
