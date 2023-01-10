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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class itemDetail extends AppCompatActivity {

    private TextView name, description, date;
    private ImageView imgItem;
    private String selectedItem;
    private FirebaseUser uid;
    private DatabaseReference rootDbRef;
    private Button btnBack, btnDeleteItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        //setting the objects
        name = findViewById(R.id.txtitemname);
        description = findViewById(R.id.txtitemDescrip);
        date = findViewById(R.id.txtitemDate);
        imgItem = findViewById(R.id.imgitemView);
        btnBack = findViewById(R.id.btnBack);
        btnDeleteItem = findViewById(R.id.btnDeleteItem);

        uid = FirebaseAuth.getInstance().getCurrentUser();
        try {
            Intent itemIntent = getIntent();
            selectedItem = itemIntent.getStringExtra("itemName");
            String itemId = itemIntent.getStringExtra("itemId");
            String itemDescription = itemIntent.getStringExtra("itemDescription");
            String itemDate = itemIntent.getStringExtra("itemDate");
            String itemImg = itemIntent.getStringExtra("itemIMG");
        //getting information from last page from intent and placing them in objects
            name.setText("Name: " + selectedItem);
            description.setText("Description: " + itemDescription);
            date.setText("Date of Creation: " + itemDate);
            Picasso.get().load(itemImg).into(imgItem);

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(itemDetail.this,MainActivity.class));
                }
            });

            btnDeleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rootDbRef = FirebaseDatabase.getInstance().getReference("Items").child(uid.getUid());
                    rootDbRef.child(itemId).removeValue();
                    Toast.makeText(itemDetail.this, "Item has been deleted", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "An error has occurred" + e.toString(), Toast.LENGTH_SHORT).show();
        }


    }
}