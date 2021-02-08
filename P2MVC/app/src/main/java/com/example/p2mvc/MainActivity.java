package com.example.p2mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Declarar Variables miembro

    private Button mNext_Button;
    private Button mPrevius_Button;
    private TextView mStudentTextView;
    private static final String TAG="etiqueta";
    private static final String KEY_INDEX="indice";

    //creacion de arreglo de objetos de la clase students
    // conexion de modelo con controlador

    studentjava[] mStudents = new studentjava[]{
            new studentjava(111,"Carlos",100),
            new studentjava(222,"Ana",80),
            new studentjava(333,"Luis",69)
    };
    //indice del arreglo
    private int mCurrentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inflar widget
        //conexion de la vista con controlador
        mNext_Button = findViewById(R.id.next_button);
        mPrevius_Button=findViewById(R.id.previous_button);
        mStudentTextView=findViewById(R.id.student_textview);
        //creacion de evento next
        mNext_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+mStudents.length+1)%(mStudents.length);
                updateStudent();
            }
        });
        mPrevius_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+mStudents.length-1)%(mStudents.length);
                updateStudent();
            }
        });
        if (savedInstanceState != null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX);
        }

        updateStudent();
        Log.d(TAG,"Oncreate() llamado");
    }
    private void updateStudent() {
        mStudentTextView.setText(
                mStudents[mCurrentIndex].getNoControl() + "\n" +
                        mStudents[mCurrentIndex].getNombre() + "\n" +
                        mStudents[mCurrentIndex].getScore()
        );
    }
    // metodo para almacenar el indice antes que se destruya
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSavedInstanceState() llamado");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() llamado");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() llamado");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() llamado");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() llamado");
    }
}
