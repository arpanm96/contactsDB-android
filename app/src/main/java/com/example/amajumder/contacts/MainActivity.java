package com.example.amajumder.contacts;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Parcelable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.example.amajumder.contacts.R;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //private SenzorApplication application;
    private ListView friendListView;
    private SearchView searchView;
    private MenuItem searchMenuItem;
   // private FriendListAdapter friendListAdapter;
   // private ArrayList<User> friendList;


    ImageButton add;
    EditText inputSearch;
    ArrayAdapter arrayAdapter;
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

        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.arrayAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    private void createList() {
        contactList.add(new Contact("ABC",123,"abc@gmal.com",R.drawable.contacticon));
        contactList.add(new Contact("ABC23",123123,"abc123@gmal.com",R.drawable.contacticon));
        contactList.add(new Contact("ABC45",123321,"abc321@gmal.com",R.drawable.contacticon));
    }


    private void createAdapter() {
        ArrayAdapter<Contact> adapter_static = new MyListAdapter();
        ListView listView = (ListView)findViewById(R.id.contactsList);
        listView.setAdapter(adapter_static);
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
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int arg = arg2 + 1;

                Bundle bundle = new Bundle();
                bundle.putInt("id",arg);

                Intent intent = new Intent(getApplicationContext(),DisplayDBContact.class);
                //Toast.makeText(MainActivity.this, "This is the DB data view! ", Toast.LENGTH_LONG).show();
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
                //finish();
                //onBackPressed();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        /*MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);*/

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // User pressed the search button
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "Searching by: "+ query, Toast.LENGTH_SHORT).show();

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            Toast.makeText(this, "Suggestion: "+ uri, Toast.LENGTH_SHORT).show();
        }
    }
}

