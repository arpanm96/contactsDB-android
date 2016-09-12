package com.example.amajumder.contacts;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList= new ArrayList<Contact>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();
        createAdapter();
    }


    Handler handler;
    Cursor cur;
    //cur = handler.getContactCursor();
    private void createList() {
        contactList.add(new Contact("ABC",123,"abc@gmal.com",R.drawable.contactIcon));
        contactList.add(new Contact("ABC23",123123,"abc123@gmal.com",R.drawable.contactIcon));
        contactList.add(new Contact("ABC45",123321,"abc321@gmal.com",R.drawable.contactIcon));
    }


    private void createAdapter() {
        ArrayAdapter<Contact> adapter = new MyListAdapter();
        ListView listView = (ListView)findViewById(R.id.contactsList);
        listView.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Contact> {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //return super.getView(position, convertView, parent);

            View itemView = convertView;
            if(itemView == null)
                itemView = getLayoutInflater().inflate(R.layout.view_item,parent,false);

            Contact cur = contactList.get(position);

            TextView name = (TextView)findViewById(R.id.contactName);
            ImageView image = (ImageView) findViewById(R.id.contactImage);

            name.setText(cur.getName());
            image.setImageResource(cur.getIconID());

            return itemView;
        }
        public MyListAdapter() {
            super(MainActivity.this,R.layout.view_item,contactList);

        }
    }
}
