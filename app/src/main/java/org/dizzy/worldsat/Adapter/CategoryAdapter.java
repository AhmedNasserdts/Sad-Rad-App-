package org.dizzy.worldsat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.dizzy.worldsat.Domain.Category;
import org.dizzy.worldsat.R;
import org.dizzy.worldsat.databinding.ViewholderCategoryholderBinding;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {




    //////////////////////////////

    private final ArrayList<Category> items ;
    private Context context;
    private int selectedPosition = 1;
    private int lastSelectedPosition = -1;

    //////////////////////////////

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }


    /////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        ViewholderCategoryholderBinding binding = ViewholderCategoryholderBinding.inflate(inflater , parent , false);

        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Viewholder holder, int position) {
        Category item = items.get(position);

        holder.binding.cateTitle.setText(item.getName());

        Glide.with(holder.itemView.getContext())
                .load(item.getImagePath())
                .into(holder.binding.imgCategory);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = selectedPosition;
                selectedPosition = position;

                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });

        holder.binding.cateTitle.setTextColor(context.getResources().getColor(R.color.white));
        if (selectedPosition==position) {
            Glide.with(holder.itemView.getContext())
                    .load(item.getImagePath()) // Restore the image
                    .into(holder.binding.imgCategory);

            holder.binding.mainCateLay.setBackgroundResource(R.drawable.blue_bg);

            holder.binding.cateTitle.setVisibility(View.VISIBLE);

        }
        else {
            Glide.with(holder.itemView.getContext())
                    .load(item.getImagePath()) // Restore the image
                    .into(holder.binding.imgCategory);
            holder.binding.mainCateLay.setBackgroundResource(0);
            holder.binding.cateTitle.setVisibility(View.GONE);
        }

    }



    @Override
    public int getItemCount() {
        return items.size();
    }


    //////////////////////////////////////////////////////////////////////////////



    public class Viewholder extends RecyclerView.ViewHolder {

        private  final ViewholderCategoryholderBinding binding;

        public Viewholder(ViewholderCategoryholderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}