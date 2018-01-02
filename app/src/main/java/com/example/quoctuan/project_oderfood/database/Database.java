package com.example.quoctuan.project_oderfood.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.quoctuan.project_oderfood.model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/30/2017.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "OrderDetail.db";
    private static final int DB_VER = 1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductName","ProductID","Quality","Price","Discount"};
        String sqlTable = "OrderDetail";

        builder.setTables(sqlTable);
        Cursor c = builder.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> rs = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                rs.add(new Order(c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quality")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))));

            }while (c.moveToNext());
        }
        return rs;
    }

    public void addToCarts(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductID,ProductName,Quality,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductID(),
                order.getProductName(),
                order.getQuality(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }
    public void deleteCarts(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }

}
