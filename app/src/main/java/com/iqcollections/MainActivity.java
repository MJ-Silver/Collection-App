package com.iqcollections;
/*
    Code Attribution 1:
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=lt6xbth-yQo&list=PL5jb9EteFAOD8dlG1Il3fCiaVNPD_P7gh&index=3&t=13s
    Title Page/Video: Android Navigation Drawer Menu Material Design | Android studio tutorial | Part 3
    Author name/tag/channel: Coding With Tea
    Author channel/profile url link: https://www.youtube.com/c/CodingWithTea
 */

/*
    Code Attribution 2:
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=eGWu0-0TWFI&t=969s
    Title Page/Video: How to Retrieve data from Firebase Database in Android Studio Retrieve data from Firebase in Android
    Author name/tag/channel: Coding With Tea
    Author channel/profile url link: https://www.youtube.com/c/CodingWithTea
 */

/*
    Code Attribution 3:
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=7GPUpvcU1FE
    Title Page/Video: RecyclerView Item Click | Best Practice Way
    Author name/tag/channel: Practical Coding
    Author channel/profile url link: https://www.youtube.com/c/PracticalCoding
 */

/*
    Code Attribution 4:
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=ayKMfVt2Sg4
    Title Page/Video: Android Login Screen | Login Android Studio | Android Studio
    Author name/tag/channel: Coding With Tea
    Author channel/profile url link: https://www.youtube.com/c/CodingWithTea
 */

/*
    Code Attribution 5:
    Source: YouTube
    URL link: https://youtu.be/9JdbgoYgCyA
    Title Page/Video: Store Firebase Realtime Database in Android Studio 2021 | Firebase Android CRUD Operation
    Author name/tag/channel: Cambo Tutorial
    Author channel/profile url link: https://m.youtube.com/c/CamboTutorial
*/

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements rvCollections, NavigationView.OnNavigationItemSelectedListener {

    private GoogleSignInOptions gsi;
    private GoogleSignInClient gsc;
    private DatabaseReference db;
    private DatabaseReference dr;
    private FirebaseStorage fs;
    private RecyclerView rv;
    private FirebaseUser uid;
    private List<readCollections> lstCollections;
    colAdapter adapter;
    static String currentCollection;
    // drawer menu variables
    DrawerLayout dl;
    NavigationView nv;
    Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding for gridview

        //Firebase

        fs = FirebaseStorage.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser();

        db = FirebaseDatabase.getInstance().getReference("Collections").child(uid.getUid());

        rv = findViewById(R.id.rvMain);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager((this)));
        lstCollections = new ArrayList<>();
        adapter = new colAdapter(MainActivity.this, lstCollections, this);
        rv.setAdapter(adapter);


        // hooks
        dl = findViewById(R.id.mainlayout);
        nv = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, R.string.navi_open, R.string.navi_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(this);

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                readCollections collections = snapshot.getValue(readCollections.class);
                lstCollections.add(collections);


                adapter.notifyDataSetChanged();
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


    }

    //do not delete this is for the options menu buttons
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_drawer, menu);
        return true;
    }

    //do not delete this is for the options menu buttons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_create:
                startActivity(new Intent(MainActivity.this, createCollection.class));
                finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

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

    public String getCurrentCollection() {
        return currentCollection;
    }

    public void setCurrentCollection(String currentCollection) {
        this.currentCollection = currentCollection;
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(MainActivity.this, listItem.class);
        intent.putExtra("selectedId", lstCollections.get(pos).getColId());
        intent.putExtra("currentcolName", lstCollections.get(pos).getColName());
        intent.putExtra("colgoal", lstCollections.get(pos).getColGoal());
        startActivity(intent);


    }
}
