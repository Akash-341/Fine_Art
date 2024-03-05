package com.example.fineart.Fragment;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fineart.Activity.MainActivity;
import com.example.fineart.R;
import com.example.fineart.databinding.FragmentLoginBinding;


public class Login extends Fragment {

    FragmentLoginBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentLoginBinding.inflate(inflater, container, false);


        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register fragmentB = new Register();

                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.conatainer, fragmentB);
                transaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
                transaction.commit();
            }
        });
        binding.tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.usernameInput.getText().toString().trim();
                String password = binding.pass.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    // Fields are empty, apply the shake animation
                    ObjectAnimator shakeAnimation = ObjectAnimator.ofFloat(binding.cardView, "translationX", -10, 10);
                    shakeAnimation.setDuration(100);
                    shakeAnimation.setRepeatCount(10);
                    shakeAnimation.setRepeatMode(ObjectAnimator.REVERSE);
                    shakeAnimation.start();
                } else {
                    // Fields are not empty, start the MainActivity
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
            }
        });
        return binding.getRoot();
    }
}