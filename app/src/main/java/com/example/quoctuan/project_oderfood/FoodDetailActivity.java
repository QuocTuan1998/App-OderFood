package com.example.quoctuan.project_oderfood;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.quoctuan.project_oderfood.database.Database;
import com.example.quoctuan.project_oderfood.model.Food;
import com.example.quoctuan.project_oderfood.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends AppCompatActivity {
    TextView txtFoodName,txtFoodPrice,txtDescription;
    ImageView imgFood;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String foodID = "";

    FirebaseDatabase database;
    DatabaseReference food;

    Food currentFood;

    Database db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        db = new Database(this);
//        FireBase
        database = FirebaseDatabase.getInstance();
        food = database.getReference("Food");

//        Init View
        numberButton = (ElegantNumberButton) findViewById(R.id.numberButton);
        btnCart = (FloatingActionButton) findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Database(getBaseContext()).addToCarts(new Order(
//                        foodID,
//                        currentFood.getName(),
//                        numberButton.getNumber(),
//                        currentFood.getPrice(),
//                        currentFood.getDiscount()
//                ));
                Order order =  new Order(
                        foodID,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount());
                db.addToCarts(order);
                Log.d("DEBUG",  foodID + " " +
                        currentFood.getName() + " " +
                        numberButton.getNumber() + " " +
                        currentFood.getPrice() + " " +
                        currentFood.getDiscount());
                Toast.makeText(FoodDetailActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();

            }
        });

        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtFoodName = (TextView) findViewById(R.id.txtFoodName);
        txtFoodPrice = (TextView) findViewById(R.id.txtFoodPrice);
        imgFood = (ImageView) findViewById(R.id.imgFood);

//       set style collapsing
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.Expand);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsing);

//        get food id from Intent
        if (getIntent() != null){
            foodID = getIntent().getStringExtra("foodID");
        }
        if (!foodID.isEmpty()){
            getDetailFood(foodID);
        }
    }

    private void getDetailFood(String foodID) {
        food.child(foodID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class);

//                set Image
                Picasso.with(getBaseContext()).load(currentFood.getImage()).into(imgFood);
                collapsingToolbarLayout.setTitle(currentFood.getName());
                txtFoodPrice.setText(currentFood.getPrice());
                txtFoodName.setText(currentFood.getName());
                txtDescription.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
