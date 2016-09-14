package com.example.amajumder.contacts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable{

    ImageButton add;
    private List<Contact> contactList= new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();
        createAdapter();
        createCallToClick();
        dbAdapter();

        addContact();
    }

    //cur = handler.getContactCursor();
    private void createList() {
        contactList.add(new Contact("ABC",123,"abc@gmal.com",R.drawable.contacticon));
        contactList.add(new Contact("ABC23",123123,"abc123@gmal.com",R.drawable.contacticon));
        contactList.add(new Contact("ABC45",123321,"abc321@gmal.com",R.drawable.contacticon));
    }


    private void createAdapter() {
        ArrayAdapter<Contact> adapter = new MyListAdapter();
        ListView listView = (ListView)findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
    }

    private void createCallToClick() {
        ListView listView = (ListView)findViewById(R.id.contactsList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact clicked =  contactList.get(position);
                //String msg = clicked.getName() + " , " + clicked.getMailID() + " , " + clicked.getPhNo();
                //Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),DisplayContact.class);

                Toast.makeText(MainActivity.this, "This is the static view! ", Toast.LENGTH_LONG).show();
                //Bundle bundle = new Bundle();
               // bundle.putSerializable("data", clicked);
                intent.putExtra("data",clicked);
                startActivity(intent);
                dbAdapter();
            }
        });
    }

    private void dbAdapter() {
        ListView listView = (ListView)findViewById(R.id.contactsList);
        DBHelper dbHelper = new DBHelper(this);
        ArrayList array_list = dbHelper.getAllCotacts();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int arg = arg2 + 1;

                Bundle bundle = new Bundle();
                bundle.putInt("id",arg);

                Intent intent = new Intent(getApplicationContext(),DisplayDBContact.class);
                Toast.makeText(MainActivity.this, "This is the DB data view! ", Toast.LENGTH_LONG).show();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void addContact() {

        ListView listView = (ListView)findViewById(R.id.contactsList);
        add = (ImageButton)findViewById(R.id.addbutton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),AddContact.class);
                // intent.putExtra("list",listView);
                startActivity(intent);
            }
        });

    }





    private class MyListAdapter extends ArrayAdapter<Contact> {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //return super.getView(position, convertView, parent);

            View itemView = convertView;
            if(itemView == null)
                itemView = getLayoutInflater().inflate(R.layout.view_item,parent,false);

            Contact cur = contactList.get(position);

            TextView name = (TextView)itemView.findViewById(R.id.contactName);
            ImageView image = (ImageView) itemView.findViewById(R.id.contactImage);

            name.setText(cur.getName());
            image.setImageResource(cur.getIconID());

            return itemView;
        }
        public MyListAdapter() {
            super(MainActivity.this,R.layout.view_item,contactList);

        }
    }
}
