package com.example.p11firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private EditText edNom, edAp, edCor, edPass;
    private ListView listv_personas;
    //List y Array
    private List<Person> listPerson=new ArrayList<>();
    ArrayAdapter<Person> arrayAdapterPersona;
    //creacion de variables para la manipulacion de FirebaseBD
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Person personSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNom=findViewById(R.id.txt_nombrePersona);
        edAp=findViewById(R.id.txt_apellidoPersona);
        edCor=findViewById(R.id.txt_correoPersona);
        edPass=findViewById(R.id.txt_passwordPersona);

        listv_personas=findViewById(R.id.lv_datosPersonas);
        // inicializa servicios de firebase
        initializeFirebase();
        //
        listData();
        listv_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                personSelected=(Person)adapterView.getItemAtPosition(i);
                edNom.setText(personSelected.getNombre());
                edAp.setText(personSelected.getApellido());
                edCor.setText(personSelected.getCorreo());
                edPass.setText(personSelected.getPassword());
            }
        });
    }

    private void listData() {
        databaseReference.child("Person").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnapShot : dataSnapshot.getChildren()){
                    Person p=objSnapShot.getValue(Person.class);
                    listPerson.add(p);
                    arrayAdapterPersona=new ArrayAdapter<Person>
                            (MainActivity.this,android.R.layout.simple_list_item_1,listPerson);
                    listv_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initializeFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String nombre = edNom.getText().toString();
        String apellido = edAp.getText().toString();
        String correo=edCor.getText().toString();
        String password=edPass.getText().toString();

        switch (item.getItemId()){
            case R.id.icon_add:{
                if (nombre.equals("")||apellido.equals("")||correo.equals("")||password.equals("")){
                    validation();
                }else{
                    Person p = new Person();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellido(apellido);
                    p.setCorreo(correo);
                    p.setPassword(password);
                    databaseReference.child("Person").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregar",Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.icon_save:{
                Person p = new Person();
                p.setUid(personSelected.getUid());
                p.setNombre(edNom.getText().toString().trim());
                p.setApellido(edAp.getText().toString().trim());
                p.setCorreo(edCor.getText().toString().trim());
                p.setPassword(edPass.getText().toString().trim());
                databaseReference.child("Person").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Guardar",Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_delete:{
                Person pe=new Person();
                pe.setUid(personSelected.getUid());
                databaseReference.child("Person").child(pe.getUid()).removeValue();
                clearText();
                Toast.makeText(this,"Eliminar",Toast.LENGTH_LONG).show();
                break;
            }
            default:break;
        }
        return true;
    }

    private void validation() {
        String nombre =edNom.getText().toString();
        String apellido =edAp.getText().toString();
        String correo=edCor.getText().toString();
        String password=edPass.getText().toString();
        if (nombre.equals("")){
            edNom.setError("Requerido");
        }else if (apellido.equals("")){
            edAp.setError("Requerido");
        }else if (correo.equals("")){
            edCor.setError("Requerido");
        }else if (password.equals("")){
            edPass.setError("Requerido");
        }
    }

    private void clearText() {
        edNom.setText("");
        edAp.setText("");
        edCor.setText("");
        edPass.setText("");
    }

}