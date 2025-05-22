package org.dizzy.worldsat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.dizzy.worldsat.Domain.ItemDomain;
import org.dizzy.worldsat.R;
import org.dizzy.worldsat.databinding.ActivityDetailBinding;

public class DetailActivity extends BasicActivity {

    ActivityDetailBinding binding;
    private ItemDomain object ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        binding.txtTitle.setText(object.getTitle());
        binding.txtPrice.setText("$" + object.getPrice());
        binding.btnBack.setOnClickListener(v -> finish());
        binding.txtBed.setText(""+object.getBed());
        binding.txtDescription.setText(object.getDescription());
        binding.txtDuration.setText(object.getDuration());
        binding.txtAddress.setText(object.getAddress());
        binding.txtDistance.setText(object.getDistance());
        binding.txtRating.setText(object.getScore()+"Rating");
        binding.ratingBar.setRating((float) object.getScore());
        Glide.with(DetailActivity.this)
                .load(object.getPic())
                .into(binding.imgPicTour);
        binding.btnAddToCard.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this , TicketActivity.class);
            intent.putExtra("object" , object);
            startActivity(intent);

        });

    }

    private void getIntentExtra() {

        object = (ItemDomain) getIntent().getSerializableExtra("object");



    }
}