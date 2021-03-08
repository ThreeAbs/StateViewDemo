package com.threeabs.stateviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

@ViewConfigure(parentID = R.id.parentID, loadingID = R.layout.layout_load, emptyID = R.layout.layout_empty)
public class MainActivity extends AppCompatActivity {

    StateView stateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateView=  StateView.getInstance().bind(this);



        findViewById(R.id.btn_show_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateView.showContent();
            }
        });

        findViewById(R.id.btn_show_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateView.showLoad();
            }
        });

        findViewById(R.id.btn_show_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateView.showEmpty();
            }
        });

        findViewById(R.id.btn_show_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateView.showError();
            }
        });

    }

}