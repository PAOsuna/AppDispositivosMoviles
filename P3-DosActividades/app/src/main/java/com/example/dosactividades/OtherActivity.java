package com.example.dosactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {
    private TextView mMessageTextView;
    private  TextView mNameTextView;
    private Button mShowButton;
    private static final String SEND_MESSAGE="mensaje";
    private static final String NAME="Carlos";
    private static final String EXTRA_NAME_SHOW="name";

    public static Intent newIntent(Context context,String message){
        Intent i=new Intent(context,OtherActivity.class);
        i.putExtra(SEND_MESSAGE, message);
        return i;
    }

    public static String wasNameShow (Intent data){
        return data.getStringExtra(EXTRA_NAME_SHOW);
    }

    public void sendBackName(String name){
        Intent data=new Intent();
        data.putExtra(EXTRA_NAME_SHOW,name);
        setResult(RESULT_OK,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        //inflar widget
        mMessageTextView=findViewById(R.id.message_textView);
        mNameTextView=findViewById(R.id.name_textView);
        mShowButton=findViewById(R.id.show_button);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=getIntent().getStringExtra(SEND_MESSAGE);
                mMessageTextView.setText(message);
                mNameTextView.setText(NAME);
                sendBackName(NAME);
            }
        });
    }
}
