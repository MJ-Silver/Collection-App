package com.iqcollections;
/*
    Code Attribution :
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=eGWu0-0TWFI&t=969s
    Title Page/Video: How to Retrieve data from Firebase Database in Android Studio Retrieve data from Firebase in Android
    Author name/tag/channel: Coding With Tea
    Author channel/profile url link: https://www.youtube.com/c/CodingWithTea
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class listItem extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference dbref;

    private ListView listview;
    private TextView txtCol;
    private String currentId;
    private String currentCol;
    private String currentGoal;
    private String goal;
    private double precentage;
    private ArrayList<String> arrayListId = new ArrayList<>();
    private ArrayList<String> arrayListName = new ArrayList<>();
    private ArrayList<String> arrayListDescription = new ArrayList<>();
    private ArrayList<String> arrayListIMG = new ArrayList<>();
    private ArrayList<String> arrayListDate = new ArrayList<>();
    private Button btnBack, btnDeleteColl;
    private DatabaseReference rootDbRef;
    private ArrayAdapter<String> adapter;
    private FirebaseUser uid;
    private static String selectedItem;
    private int counter;
    private ProgressBar goalProgess;

    DrawerLayout dl;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting objects
        setContentView(R.layout.activity_list_item);
        uid = FirebaseAuth.getInstance().getCurrentUser();
        dbref = FirebaseDatabase.getInstance().getReference("Items").child(uid.getUid());
        txtCol = findViewById(R.id.txtItemCollection);
        listview = (ListView) findViewById(R.id.lstItemsview);
        goalProgess = findViewById(R.id.goalProg);
        btnBack = findViewById(R.id.btnBack);
        btnDeleteColl = findViewById(R.id.btnDeleteColl);
//setting list view adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, arrayListName);
        listview.setAdapter(adapter);
        Intent intent = getIntent();
        //getting collection information from last page
        currentId = intent.getStringExtra("selectedId");
        currentCol = intent.getStringExtra("currentcolName");
        currentGoal = intent.getStringExtra("colgoal");
        counter = 0;
        double curgoal = Double.parseDouble(currentGoal);

        dl = findViewById(R.id.itemDisplayLayout);
        nv = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, R.string.navi_open, R.string.navi_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(this);
        //getting list items
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                readItems value = snapshot.getValue(readItems.class);
                if (value != null) {//setting list and goal information
                    if (value.getItemCollection().equals(currentCol)) {
                        arrayListId.add(value.getItemId());
                        arrayListName.add(value.getItemName());
                        arrayListDescription.add(value.getItemDescription());
                        arrayListDate.add(value.getItemDate());
                        arrayListIMG.add(value.getItemImage());
                        counter++;
                        precentage = (counter / curgoal) * 100;
                        String itemscol = "Items available for  " + currentCol + ":  Goal progress: " + counter + "/" + currentGoal + "  " + precentage + "%";
                        goalProgess.setMax(Integer.parseInt(currentGoal));
                        goalProgess.setProgress(counter);
                        goalProgess.refreshDrawableState();

                        txtCol.setText(itemscol);
                    }


                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent itemIntent = new Intent(listItem.this, itemDetail.class);
                itemIntent.putExtra("itemId", arrayListId.get(i));
                itemIntent.putExtra("itemName", arrayListName.get(i));
                itemIntent.putExtra("itemDescription", arrayListDescription.get(i));
                itemIntent.putExtra("itemDate", arrayListDate.get(i));
                itemIntent.putExtra("itemIMG", arrayListIMG.get(i));

                startActivity(itemIntent);


            }
        });

        btnDeleteColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootDbRef = FirebaseDatabase.getInstance().getReference("Collections").child(uid.getUid());
                rootDbRef.child(currentId).removeValue();
                Toast.makeText(listItem.this, "This collection has been deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(listItem.this,MainActivity.class));
            }
        });
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    //do not delete this is for the options menu buttons
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_items, menu);
        return true;
    }

    //do not delete this is for the options menu buttons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_create:
                Intent intent = new Intent(listItem.this, createItem.class);

                intent.putExtra("colName", currentCol);

                startActivity(intent);
                finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_wish:
                intent = new Intent(this, wishlist.class);
                startActivity(intent);

                break;
            case R.id.nav_member:
                intent = new Intent(this, groupMembers.class);
                startActivity(intent);
                break;
            case R.id.nav_about:
                intent = new Intent(this, aboutDisplay.class);
                startActivity(intent);
                break;
            case R.id.nav_graph:
                intent = new Intent(this, Graph.class);
                startActivity(intent);
                break;


        }
        dl.closeDrawer(GravityCompat.START);
        return true;
    }
}


