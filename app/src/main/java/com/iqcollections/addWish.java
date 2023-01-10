package com.iqcollections;

/*
    Code Attribution 1:
    Source: YouTube
    URL link: https://youtu.be/9JdbgoYgCyA?list=PLHQRWugvckFr9H2Mo4hyre1wQHglSRake
    Title Page/Video: How to Insert Data in Firebase Realtime Database | Android Firebase Part 3
    Author name/tag/channel: CodingZest
    Author channel/profile url link: https://www.youtube.com/c/CodingZest
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addWish extends AppCompatActivity {

    private EditText etName;
    private EditText etDesc;
    private EditText etPrice;
    private FirebaseUser uid;
    private FirebaseAuth mAuth;
    private Button btnAdd;
    private Button btnShowWish;

    private DatabaseReference wishlistDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);
        // Using a try catch for error handling
        try {

            mAuth = FirebaseAuth.getInstance();
            etName = findViewById(R.id.wishName);
            etDesc = findViewById(R.id.wishDesc);
            etPrice = findViewById(R.id.wishPrice);
            btnAdd = findViewById(R.id.btnAddWish);
            btnShowWish = findViewById(R.id.btnShowWish);

            //Creating the wishlist table in the firebase
            wishlistDbRef = FirebaseDatabase.getInstance().getReference().child("Wishlist");
            uid = FirebaseAuth.getInstance().getCurrentUser();// setting the main user

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = etName.getText().toString();
                    String desc = etDesc.getText().toString();
                    String price = etPrice.getText().toString();

                    // using if statement to ensure that the user does not insert empty data
                    if (name.isEmpty() || desc.isEmpty() || price.isEmpty()) {
                        Toast.makeText(addWish.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        addWishlistData();
                        // return to the wishlist activity
                        Context context = view.getContext();
                        Intent intent = new Intent(context, wishlist.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(addWish.this, "Data inserted!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnShowWish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(addWish.this, wishlist.class));
                    finish();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "An error has occurred" + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void addWishlistData() {
        try {
            String id = wishlistDbRef.child(uid.getUid()).push().getKey();
            String name = etName.getText().toString();
            String desc = etDesc.getText().toString();
            String price = etPrice.getText().toString();

            // using if statement to ensure that the user does not insert empty data
            if (name.isEmpty() || desc.isEmpty() || price.isEmpty()) {
                Toast.makeText(addWish.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            } else {

                wishClass wishClass = new wishClass(id, name, desc, price);
                wishlistDbRef.child(uid.getUid()).child(id).setValue(wishClass);
            }
        } catch (Exception e) {
            Toast.makeText(this, "An error has occurred" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}