package com.example.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fineart.Ui.Fragment.Login;
import com.example.fineart.R;

public class Authorization_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);


        if (savedInstanceState == null) {
            // Add the fragment to the container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.conatainer, new Login())
                    .commit();
        }






    }
}