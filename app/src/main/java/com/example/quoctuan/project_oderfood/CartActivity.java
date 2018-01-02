package com.example.quoctuan.project_oderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.quoctuan.project_oderfood.database.Database;
import com.example.quoctuan.project_oderfood.model.Order;
import com.example.quoctuan.project_oderfood.viewholder.CardAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView txtTotal;
    FButton btnPlace;

    List<Order> cart = new ArrayList<>();
    CardAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        FireBase
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Request");

//        Init
        recyclerView = (RecyclerView) findViewById(R.id.recyclerCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotal = (TextView) findViewById(R.id.txtTotal);
        btnPlace = (FButton) findViewById(R.id.btnPlace);

        loadListFood();

    }

    private void loadListFood() {

        cart = new Database(this).getCarts();
        adapter = new CardAdapter(cart,this);
        recyclerView.setAdapter(adapter);

//        calculator total of price
        int total = 0;
        for (Order order : cart){
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuality()));
        }

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotal.setText(fmt.format(total));


    }
}
