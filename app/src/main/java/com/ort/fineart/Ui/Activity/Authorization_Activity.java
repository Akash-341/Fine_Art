package com.ort.fineart.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ort.fineart.R;
import com.ort.fineart.Ui.Fragment.Login;

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