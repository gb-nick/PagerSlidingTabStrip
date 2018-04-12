package com.example.tabstrip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button button, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.button) {
            intent.setClass(this, TextTabActivity.class);
        } else if (v.getId() == R.id.button2) {
            intent.setClass(this, IconTabActivity.class);
        } else if (v.getId() == R.id.button3) {
            intent.setClass(this, TabActivity.class);
        }
        startActivity(intent);
    }
}
