package org.dizzy.worldsat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.dizzy.worldsat.Activity.DetailActivity;
import org.dizzy.worldsat.Activity.MainActivity;
import org.dizzy.worldsat.Domain.ItemDomain;
import org.dizzy.worldsat.databinding.ViewholderPopularBinding;
import org.dizzy.worldsat.databinding.ViewholderRecommendedBinding;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {

    public PopularAdapter(ArrayList<ItemDomain> items) {
        this.items = items;
    }

    ArrayList<ItemDomain> items = new ArrayList<ItemDomain>();
    ViewholderPopularBinding binding;
    Context context;

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewholderPopularBinding.inflate(             LayoutInflater.from(parent.getContext())    , parent   , false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {

        binding.txtTitle.setText(items.get(position).getTitle());
        binding.txtPrice.setText("$"+items.get(position).getPrice());
        binding.txtAddress.setText(items.get(position).getAddress());
        binding.txtScore.setText(""+items.get(position).getScore());

        Glide.with(context)
                .load(items.get(position).getPic())
                .into(binding.imgPopular);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("object" , items.get(position));
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull ViewholderPopularBinding binding) {
            super(binding.getRoot());
        }
    }
}
