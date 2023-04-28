package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dbdemo.data.MyDbHelper;
import com.example.dbdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHelper db=new MyDbHelper(MainActivity.this);

        Contact me = new Contact();
        me.setName("jsbajd");
        me.setPhone("9714932");
        db.addContact(me);
        ArrayList<String> contacts=new ArrayList<>();
        ListView listView=findViewById(R.id.listView);
        List<Contact> list=db.getAllContacts();
        for(Contact contact:list){
            Log.d("yo","ID: "+contact.getId()+" Name: "+contact.getName()+" Phone: "+contact.getPhone());
            contacts.add(contact.getName()+"("+contact.getPhone()+")");
        }
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"You selected contact number "+position,Toast.LENGTH_LONG).show();
            }
        });
    }
}