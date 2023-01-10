package com.iqcollections;

/*
    Code Attribution 1:
    Source: YouTube
    Source URL link: https://www.youtube.com/watch?v=XactTKR0Wfc
    Title Page/Video: Firebase Android Tutorial 5 - Retrieving Data from Firebase Realtime Database
    Author name/tag/channel: ProgrammingKnowledge
    Author channel/profile url link: https://www.youtube.com/c/ProgrammingKnowledge
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class wishlist extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String WISHID = "wishId";
    public static final String WISHNAME = "wishName";
    public static final String WISHDESCR = "wishDescr";
    public static final String WISHPRICE = "wishPrice";

    private ListView wishlistView;
    private FirebaseUser uid;
    List<wishClass> wishClassList;
    private DatabaseReference dbref;
    DrawerLayout dl;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        wishlistView = findViewById(R.id.wishlistView);
        uid = FirebaseAuth.getInstance().getCurrentUser();// setting the main user
//        ArrayList<String> list = new ArrayList<>();//Using an arraylist to store the data from firebase
//        ArrayAdapter adapter = new ArrayAdapter<String>(wishlist.this, R.layout.wishlist_item, list);
//        wishlistView.setAdapter(adapter);

        dbref = FirebaseDatabase.getInstance().getReference("Wishlist").child(uid.getUid());
        wishClassList = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Wishlist").child(uid.getUid());// database reference to call the data

        dl = findViewById(R.id.wishlistLayout);
        nv = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, R.string.navi_open, R.string.navi_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(this);

        wishlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                wishClass wish = wishClassList.get(i);

                Intent intent = new Intent(getApplicationContext(), WishlistItemDetail.class);
                intent.putExtra(WISHID, wish.getWishId());
                intent.putExtra(WISHNAME, wish.getWishName());
                intent.putExtra(WISHDESCR, wish.getWishDesc());
                intent.putExtra(WISHPRICE, wish.getWishPrice());

                startActivity(intent);
            }
        });
/*
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();// clear the list
                // using a DataSnapshot that contains data from the database location to read the data in the database
                for (DataSnapshot wishSnapshot : snapshot.getChildren()) {
                    wishClass wList = wishSnapshot.getValue(wishClass.class);// getting the value from the wishClass class
                    String txt = "Wish Item: " + wList.getWishName() +
                            " \nWish Description: " + wList.getWishDesc() +
                            " \nExpected Price: R" + wList.getWishPrice();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

 */
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wishClassList.clear();
                for(DataSnapshot wishlistSnapshot : snapshot.getChildren())
                {
                    wishClass wish = wishlistSnapshot.getValue(wishClass.class);

                    wishClassList.add(wish);
                }
                WListList adapter = new WListList(wishlist.this, wishClassList);
                wishlistView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // calling the nav menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_wish, menu);
        return true;
    }

    //do not delete this is for the options menu buttons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_addwish:
                startActivity(new Intent(wishlist.this, addWish.class));
                finish();
                break;
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

}