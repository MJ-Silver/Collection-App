package com.iqcollections;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.iqcollections.databinding.ActivityFavouriteBinding;

public class favourite extends AppCompatActivity {

    ActivityFavouriteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] collectionName = {"Car","IT","PC"};
        //pull from db images and names
        int[] collectionIMg = {R.drawable.background,R.drawable.background,R.drawable.background};

        grid_adapter gridAdapter = new grid_adapter(favourite.this, collectionName, collectionIMg) {
        };
        binding.grdFav.setAdapter(gridAdapter);


        binding.grdFav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(favourite.this,"You Clicked on "+ collectionName[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_favourites,menu);
        return true;
    }
}