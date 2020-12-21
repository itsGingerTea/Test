package com.example.testperetz;

import android.content.Context;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;

    public FoodAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        sharedPreferences = context.getSharedPreferences("save_count", Context.MODE_PRIVATE);
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
        public int count = 0;
        public ListItem itemId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewCount = (TextView) itemView.findViewById(R.id.textViewCount);
            buttonPlus = (Button) itemView.findViewById(R.id.buttonPlus);
            buttonMinus = (Button) itemView.findViewById(R.id.buttonMinus);
        }

        private void saveCount(String id, int count) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(id, count);
            editor.apply();
        }

        private int loadCount(String id) {
            return sharedPreferences.getInt(id, 0);
        }

        public void bind(ListItem item) {
            itemId = item;
            textViewName.setText(item.getName());
            textViewPrice.setText(item.getPrice() + " ₽");

            count = loadCount(itemId.getId());
            if (count > 0) {
                textViewCount.setVisibility(View.VISIBLE);
                buttonMinus.setVisibility(View.VISIBLE);
            }
            else {
                textViewCount.setVisibility(View.INVISIBLE);
                buttonMinus.setVisibility(View.INVISIBLE);
            }

            buttonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                    textViewCount.setText(String.valueOf(count));
                    saveCount(itemId.getId(), count);

                    if (count < 1) {
                        textViewCount.setVisibility(View.INVISIBLE);
                    } else {
                        textViewCount.setVisibility(View.VISIBLE);
                        buttonMinus.setVisibility(View.VISIBLE);
                    }
                }
            });

            buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count--;
                    textViewCount.setText(String.valueOf(count));
                    saveCount(itemId.getId(), count);

                    if (count < 1) {
                        textViewCount.setVisibility(View.INVISIBLE);
                        buttonMinus.setVisibility(View.INVISIBLE);
                    } else {
                        textViewCount.setVisibility(View.VISIBLE);
                    }
                }
            });

            Picasso.get()
                    .load(item.getImageUrl())
                    .resize(300, 300)
                    .centerCrop()
                    .into(imageView);
        }

    }

}
