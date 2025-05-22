package org.dizzy.worldsat.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.dizzy.worldsat.Domain.ItemDomain;
import org.dizzy.worldsat.R;
import org.dizzy.worldsat.databinding.ActivityTicketBinding;

public class TicketActivity extends BasicActivity {
    ActivityTicketBinding binding;
    ItemDomain object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();

    }

    private void setVariable() {
        Glide.with(TicketActivity.this)
                .load(object.getPic())
                .into(binding.imgTicket);

        Glide.with(TicketActivity.this)
                .load(object.getTourGuidePic())
                .into(binding.imgTourGuide);

        binding.btnBack.setOnClickListener(v -> finish());
        binding.txtTitle.setText(object.getTitle());
        binding.txtName.setText(object.getTourGuideName());
        binding.txtTime.setText(object.getTimeTour());
        binding.txtTourguide.setText(object.getTourGuideName());
        binding.txtTotalguest.setText(""+object.getBed());
        binding.txtDuration.setText(object.getDuration());
        binding.btnCall.setOnClickListener(v -> {
            String phone = object.getTourGuidePhone();

            if (phone != null && !phone.isEmpty()) {
                try {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("smsto:" + phone));  // Corrected URI scheme
                    sendIntent.putExtra("sms_body", "Write Your Message");
                    v.getContext().startActivity(sendIntent);
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "No app found to send SMS", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(v.getContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = object.getTourGuidePhone();

                if (phone != null && !phone.isEmpty()) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));  // Corrected "tel" scheme
                        v.getContext().startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(v.getContext(), "No app found to make a call", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void getIntentExtra() {
        object = (ItemDomain) getIntent().getSerializableExtra("object");
    }


}