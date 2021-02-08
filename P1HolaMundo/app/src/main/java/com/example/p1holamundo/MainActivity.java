package com.example.p1holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // creacion de variables miembro

    private Button mHello_Button;
    private Button mWorld_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inflar widget
        mHello_Button = findViewById(R.id.hello_button);
        //    creacion de evento onClick
        mHello_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.hello, Toast.LENGTH_SHORT).show();
            }

        });

        mWorld_button = findViewById(R.id.world_button);
        mWorld_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Mundo",Toast.LENGTH_SHORT).show();
            }
        });

    }
}



