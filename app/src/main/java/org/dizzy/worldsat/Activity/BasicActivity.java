package org.dizzy.worldsat.Activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class BasicActivity extends AppCompatActivity {

   FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Window widow = getWindow();
        widow.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS ,
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );


    }
}