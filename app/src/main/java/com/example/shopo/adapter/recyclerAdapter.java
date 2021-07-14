package com.example.shopo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopo.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.cardDesign>{
    private ArrayList<String> itemName;
    private ArrayList<String> itemPrice;
    private ArrayList<Integer> itemImage;
    private Context context;

    public recyclerAdapter(ArrayList<String> itemName, ArrayList<String> itemPrice, ArrayList<Integer> itemImage, Context context) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.context = context;
    }


    @Override
    public cardDesign onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);
        return new cardDesign(view);
    }

    @Override
    public void onBindViewHolder(cardDesign holder, int position) {
        holder.itemName.setText(itemName.get(position));
        holder.itemPrice.setText(itemPrice.get(position));
        holder.imageView.setImageResource(itemImage.get(position));
        holder.cardView.setOnClickListener(v -> {
            if(itemName.get(position).equalsIgnoreCase("Empty Cart!"));
            else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                dialog.setTitle("Do you want to Delete Item?")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemName.remove(position);
                                itemPrice.remove(position);
                                itemImage.remove(position);
                                notifyDataSetChanged();

                                if(itemName.size()==0){
                                    itemName.add("Empty Cart!");
                                    itemPrice.add("OOPSIE!!!");
                                    itemImage.add(R.drawable.emptycart);
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("saveData",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.remove("orders");
                                    editor.apply();
                                }
                                else{
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("saveData",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    Set<String> orders = new HashSet<>();
                                    for (int i = 0; i < itemImage.size(); i++) {
                                        orders.add(itemName.get(i)+","+itemPrice.get(i)+","+itemImage.get(i));
                                    }

                                    editor.putStringSet("orders",orders);
                                    editor.apply();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();
                }


        });
    }

    @Override
    public int getItemCount() {
        return itemName.size();
    }

    public class cardDesign extends RecyclerView.ViewHolder {

        private TextView itemName,itemPrice;
        private ImageView imageView;
        private CardView cardView;

        public cardDesign(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.nameMen);
            itemPrice = itemView.findViewById(R.id.priceMen);
            imageView = itemView.findViewById(R.id.itemImg);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
