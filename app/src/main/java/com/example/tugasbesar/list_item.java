package com.example.tugasbesar;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class list_item extends ArrayAdapter<Mahasiswa> {

    private Activity context;
    private List<Mahasiswa> mahasiswalist;

    public list_item(Activity context, List<Mahasiswa> mahasiswalist){
        super(context, R.layout.activity_listlayout, mahasiswalist);
        this.context = context;
        this.mahasiswalist = mahasiswalist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.activity_listlayout, null, true);

        TextView textViewName = (TextView) ListViewItem.findViewById(R.id.textViewname);
        TextView textViewNim = (TextView) ListViewItem.findViewById(R.id.textViewnim);
        TextView textViewProdi = (TextView) ListViewItem.findViewById(R.id.textViewprodi);

        Mahasiswa mahasiswa = mahasiswalist.get(position);

        textViewName.setText(mahasiswa.getNama());
        textViewNim.setText(mahasiswa.getNim());
        textViewProdi.setText(mahasiswa.getProdi());

        return ListViewItem;
    }
}
