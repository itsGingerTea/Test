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
    public int count = 0;

    public FoodAdapter(List<ListItem> listItems) {
        this.listItems = listItems;
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
        holder.bind(listItems.get(position));
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

        public void bind(ListItem item) {
            textViewName.setText(item.getName());
            textViewPrice.setText(item.getPrice() + " ₽");

            buttonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    textViewCount.setText(String.valueOf(count));

                    if (count < 1) {
                        textViewCount.setVisibility(View.INVISIBLE);
                    } else {
                        textViewCount.setVisibility(View.VISIBLE);
                    }
                }
            });

            buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count--;
                    textViewCount.setText(String.valueOf(count));

                    if (count < 1) {
                        textViewCount.setVisibility(View.INVISIBLE);
                    } else {
                        textViewCount.setVisibility(View.VISIBLE);
                    }
                }
            });

            Picasso.get()
                    .load(item.getImageUrl())
                    .resize(350, 350)
                    .centerCrop()
                    .into(imageView);
        }

    }

}
