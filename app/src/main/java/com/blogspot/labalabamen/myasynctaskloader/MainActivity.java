package com.blogspot.labalabamen.myasynctaskloader;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<ArrayList<WeatherItems>> {
    ListView listView ;
    WeatherAdapter adapter;
    EditText editKota;
    Button buttonCari;

    static final String EXTRA_CITY = "EXTRA_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new WeatherAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(adapter);

        editKota =(EditText)findViewById(R.id.edit_kota);
        buttonCari = (Button)findViewById(R.id.btn_kota);


        buttonCari.setOnClickListener(myListener);

        String kota = editKota.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CITY,kota);

        getLoaderManager().initLoader(0, bundle, this);



    }



    // dibawah ini fungsi menjalankan proses myasynctaskloader

    @Override
    public android.content.Loader<ArrayList<WeatherItems>> onCreateLoader(int id, Bundle args) {
        String kumpulanKota = "";
        if (args != null){
            kumpulanKota = args.getString(EXTRA_CITY);
        }

        return new MyAsyncTaskLoader(this, kumpulanKota);
    }

    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<WeatherItems>> loader, ArrayList<WeatherItems> data) {
        adapter.setData(data);

    }

    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<WeatherItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String kota = editKota.getText().toString();

            if (TextUtils.isEmpty(kota))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_CITY,kota);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
