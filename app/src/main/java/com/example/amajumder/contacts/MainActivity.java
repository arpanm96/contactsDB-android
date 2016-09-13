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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable{

    private List<Contact> contactList= new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();
        createAdapter();
        createCallToClick();

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

                //Bundle bundle = new Bundle();
               // bundle.putSerializable("data", clicked);
                intent.putExtra("data",clicked);
                startActivity(intent);
            }
        });
    }

    /*private void addContact() {

        ListView listView = (ListView)findViewById(R.id.contactsList);


    }*/





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
