package com.example.quoctuan.project_oderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quoctuan.project_oderfood.Interface.ItemClickListener;
import com.example.quoctuan.project_oderfood.model.Food;
import com.example.quoctuan.project_oderfood.viewholder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String categoryID = "";

    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

//        Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_Food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


//        get Intent and CategoryID
        if (getIntent() != null){
            categoryID = getIntent().getStringExtra("CategoryID");
        }
        if (!categoryID.isEmpty() && categoryID != null){
            loadListFood(categoryID);
        }


    }

    private void loadListFood(String categoryID) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuId").equalTo(categoryID) //Select * From Food Where MenuID = "CategoryID"
                ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);

                final Food local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
//                Start new Activity
                        Intent intentDetails = new Intent(FoodListActivity.this,FoodDetailActivity.class);
                        intentDetails.putExtra("foodID",adapter.getRef(position).getKey());//send food id
                        startActivity(intentDetails);

                    }
                });
            }
        };
//        set adapter
        Log.d("TAG","" + adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
