package com.example.testperetz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public int count = 0;

    public FoodAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        ListItem listItem = listItems.get(position);


        holder.textViewName.setText(listItem.getName());
        holder.textViewPrice.setText(listItem.getPrice() + " ₽");
        //holder.textViewCount.setText(String.valueOf(count));

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                holder.textViewCount.setText(String.valueOf(count));  //holder is final. Why?

                if (count < 1) {
                    holder.textViewCount.setVisibility(View.INVISIBLE);
                } else {
                    holder.textViewCount.setVisibility(View.VISIBLE);
                }


            }
        });

        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                holder.textViewCount.setText(String.valueOf(count));  //holder is final. Why?

                if (count < 1) {
                    holder.textViewCount.setVisibility(View.INVISIBLE);
                } else {
                    holder.textViewCount.setVisibility(View.VISIBLE);
                }

            }
        });

        Picasso.get()
        .load(listItem.getImageUrl())
        .resize(350,350)
        .centerCrop()
        .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewPrice;
        public ImageView imageView;
        public TextView textViewCount;
        public Button buttonPlus;
        public Button buttonMinus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewCount = (TextView) itemView.findViewById(R.id.textViewCount);
            buttonPlus = (Button) itemView.findViewById(R.id.buttonPlus);
            buttonMinus = (Button) itemView.findViewById(R.id.buttonMinus);
        }


    }

}
