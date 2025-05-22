package org.dizzy.worldsat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.dizzy.worldsat.databinding.ActivityIntroBinding;

public class IntroActivity extends BasicActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.introBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}