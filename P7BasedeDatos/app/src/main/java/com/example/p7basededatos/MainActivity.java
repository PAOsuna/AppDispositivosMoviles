package com.example.p7basededatos;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    //declaramos variables miembro
    private GridView mGridView;
    public ArrayList<String> ArrayOfName=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setListAdapter(new ArrayAdapter<String>(this,
                R.layout.activity_main,ArrayOfName));
        //creamos objeto de la clase DatabaseHelper
        DatabaseHelper db=new DatabaseHelper(this);
        //CRUD
        //Creamos objeto de la clase Contact
        Contact juan=new Contact(1,"Juan","6641234");
        Contact maria=new Contact(2,"Maria","66424421");
        Contact luis=new Contact(3, "Luis","8412211");
        Contact ana=new Contact(4,"Ana","212142");
        db.addContacts(juan);
        db.addContacts(maria);
        db.addContacts(luis);
        db.addContacts(ana);
        //borrar un contacto
        db.deleteContact(luis);
        //obtener lista de contactos
        List<Contact> contacts=db.getAllContacts();
        for (Contact cn:contacts){
            String cont=cn.getName()+"\n"+cn.getPhoneNumber();
            //Escribe contactos a cont
            ArrayOfName.add(cont);
        }
        //Regresa la cuenta de contactos
        ArrayOfName.add("Cuenta:"+db.getContactsCount());
        //Actualizar Contactos
        db.updateContact(2,"Pedro","4246422");
        Contact consulta=db.getContact(2);
        ArrayOfName.add(consulta.getName()+"\n"+
                consulta.getPhoneNumber());
        ListView listView=getListView();
        listView.setTextFilterEnabled(true);
    }
}
