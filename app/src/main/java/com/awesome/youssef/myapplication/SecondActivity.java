package com.awesome.youssef.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.punchthrough.bean.sdk.Bean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.punchthrough.bean.sdk.BeanDiscoveryListener;
import com.punchthrough.bean.sdk.BeanManager;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                goToThirdActivity();
            }

        });
    }

    public void goToThirdActivity() {

        final List<Bean> beans = new ArrayList<>();
        final List<String> names = new ArrayList<>();


        setContentView(R.layout.activity_second);

        final ListView lv = (ListView) findViewById(R.id.toto);

        // Instanciating an array list (you don't need to do this,

        BeanDiscoveryListener listener = new BeanDiscoveryListener() {


            @Override
            public void onBeanDiscovered(Bean bean, int rssi) {
                beans.add(bean);
                names.add(bean.getDevice().getName());
            }

            @Override
            public void onDiscoveryComplete() {

                for (Bean bean : beans) {
                    System.out.println(bean.getDevice().getName());   // "Bean"              (example)
                    System.out.println(bean.getDevice().getAddress());    // "B4:99:4C:1E:BC:75" (example)


                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        names);

                lv.setAdapter(arrayAdapter);
            }
        };

        BeanManager.getInstance().setScanTimeout(30);  // Timeout in seconds, optional, default is 30 seconds
        BeanManager.getInstance().startDiscovery(listener);

    }

}



