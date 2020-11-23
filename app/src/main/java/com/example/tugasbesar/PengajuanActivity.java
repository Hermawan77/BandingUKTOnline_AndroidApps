package com.example.tugasbesar;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class PengajuanActivity extends AppCompatActivity {
    Button selectFile, upload, submit;
    EditText nama, nim, prodi;
    DatabaseReference reff;
    Mahasiswa mahasiswa;
    TextView notification;
    Uri pdfUri;

    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    ArrayList<String> urls=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan);

        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();

        nama = findViewById(R.id.nama_1);
        nim = findViewById(R.id.nim_1);
        prodi = findViewById(R.id.prodi_1);
        mahasiswa = new Mahasiswa();
        selectFile=findViewById(R.id.selectFile);
        upload=findViewById(R.id.upload);
        notification=findViewById(R.id.notification);
        reff= FirebaseDatabase.getInstance().getReference().child("Mahasiswa");

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PengajuanActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                    mahasiswa.setNama(nama.getText().toString().trim());
                    mahasiswa.setNim(nim.getText().toString().trim());
                    mahasiswa.setProdi(prodi.getText().toString().trim());
                    reff.push().setValue(mahasiswa);
                }
                else{
                    ActivityCompat.requestPermissions(PengajuanActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pdfUri!=null){
                    uploadFile(pdfUri);
                }
                else{
                    Toast.makeText(PengajuanActivity.this, "Select a File", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void uploadFile(Uri pdfUri){
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String fileName=System.currentTimeMillis()+".pdf";
        StorageReference storageReference=storage.getReference();

        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(PengajuanActivity.this, "Data Succesfully Submited", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(PengajuanActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress= (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
                if (currentProgress == 100){
                    progressDialog.dismiss();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
    }

    private void selectPdf(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri=data.getData();
            notification.setText("A file is selected : "+ data.getData().getLastPathSegment());
        }
        else{
            Toast.makeText(PengajuanActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
    }

}