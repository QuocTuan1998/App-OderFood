package com.example.quoctuan.project_oderfood.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.quoctuan.project_oderfood.Interface.ItemClickListener;
import com.example.quoctuan.project_oderfood.R;
import com.example.quoctuan.project_oderfood.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 12/31/2017.
 */


class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtCartName,txtCartPrice;
    public ImageView imgCount;

    private ItemClickListener itemClickListener;

    public void setTxtCartName(TextView txtCartName){
        this.txtCartName = txtCartName;
    }

    public CartViewHolder(View itemView) {
        super(itemView);

        txtCartName = (TextView) itemView.findViewById(R.id.txtCartName);
        txtCartPrice = (TextView) itemView.findViewById(R.id.txtCartPrice);
        imgCount = (ImageView) itemView.findViewById(R.id.imgCount);

    }

    @Override
    public void onClick(View v) {

    }
}

public class CardAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> list = new ArrayList<>();
    private Context context;

    public CardAdapter(List<Order> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);

        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder().buildRound("" + list.get(position).getQuality(), Color.RED);
        holder.imgCount.setImageDrawable(drawable);

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(list.get(position).getPrice())) * (Integer.parseInt(list.get(position).getQuality()));
        holder.txtCartPrice.setText(fmt.format(price));
        holder.txtCartName.setText(list.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
