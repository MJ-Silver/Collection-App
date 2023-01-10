package com.iqcollections;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WishlistItemDetail extends AppCompatActivity {

    private TextView name, description, price;
    private FirebaseUser uid;
    private DatabaseReference rootDbRef;
    private Button btnBack, btnDeleteWishItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_item_detail);

        name = findViewById(R.id.txtWishName);
        description = findViewById(R.id.txtWishDescr);
        price = findViewById(R.id.txtWishPrice);
        btnBack = findViewById(R.id.btnBack);
        btnDeleteWishItem = findViewById(R.id.btnDeleteWishItem);
        uid = FirebaseAuth.getInstance().getCurrentUser();
        try {
            Intent intent = getIntent();

            String wishId = intent.getStringExtra(wishlist.WISHID);
            String wishName = intent.getStringExtra(wishlist.WISHNAME);
            String wishDescr = intent.getStringExtra(wishlist.WISHDESCR);
            String wishPrice = intent.getStringExtra(wishlist.WISHPRICE);

            name.setText("Item name: " + wishName);
            description.setText("Description: " + wishDescr);
            price.setText("Estimated price: R" + wishPrice);

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(WishlistItemDetail.this, wishlist.class));
                }
            });

            btnDeleteWishItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rootDbRef = FirebaseDatabase.getInstance().getReference("Wishlist").child(uid.getUid());
                    rootDbRef.child(wishId).removeValue();
                    Toast.makeText(WishlistItemDetail.this, "Wishlist item has been deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "An error has occured" + e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}