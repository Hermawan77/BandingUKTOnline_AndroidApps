package com.example.tugasbesar;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class PersyaratanActivity extends AppCompatActivity{
    TextView kades, kaprodi, kmitera;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref, ref1, ref2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persyaratan);

        kades = findViewById(R.id.kades);
        kaprodi = findViewById(R.id.kaprodi);
        kmitera = findViewById(R.id.kmitera);

        kades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download();
            }
        });
        kmitera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download1();
            }
        });
        kaprodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download2();
            }
        });
    }

    public void download(){
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("Surat_Persetujuan_Kepala_Desa.pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(PersyaratanActivity.this, "Surat_Persetujuan_Kepala_Desa", ".pdf", DIRECTORY_DOWNLOADS, url);
                Toast.makeText(getApplicationContext(), "Mengunduh", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Unduhan Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void download1(){
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("Surat_Persetujuan_KM_ITERA.pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(PersyaratanActivity.this, "Surat_Persetujuan_KM_ITERA", ".pdf", DIRECTORY_DOWNLOADS, url);
                Toast.makeText(getApplicationContext(), "Mengunduh", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Unduhan Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void download2(){
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("Surat_Persetujuan_DosenWali_Kaprodi.pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(PersyaratanActivity.this, "Surat_Persetujuan_DosenWali_Kaprodi", ".pdf", DIRECTORY_DOWNLOADS, url);
                Toast.makeText(getApplicationContext(), "Mengunduh", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Unduhan Gagal", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName +  fileExtension);

        downloadmanager.enqueue(request);
    }

}