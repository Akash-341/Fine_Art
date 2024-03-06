package com.example.fineart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fineart.Interface.ApiService;
import com.example.fineart.Model.Payload;
import com.example.fineart.Model.RegistrationApiResponse;
import com.example.fineart.R;
import com.example.fineart.RetrofitClient;
import com.example.fineart.databinding.FragmentRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends Fragment {

    FragmentRegisterBinding binding;
    private ApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentRegisterBinding.inflate(inflater,container,false);
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        binding.tvlogin.setOnClickListener(view -> {
            String firstName = binding.firstName.getText().toString();
            String lastName = binding.LastName.getText().toString();
            String email = binding.email.getText().toString();
            String phoneNumber = binding.phoneNumber.getText().toString();
            String password = binding.pass.getText().toString();
            String confirmPassword = binding.confirmPass.getText().toString();

            if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                    TextUtils.isEmpty(email) || TextUtils.isEmpty(phoneNumber) ||
                    TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                // Show an error message or handle validation as needed
                Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                // Show an error message for password mismatch
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                // Call a method to perform user registration API call
                registerUser();

        }
        });
        return binding.getRoot();
    }
    private void registerUser() {
        String firstName = binding.firstName.getText().toString();
        String lastName = binding.LastName.getText().toString();
        String email = binding.email.getText().toString();
        String phoneNumber = binding.phoneNumber.getText().toString();
        String password = binding.pass.getText().toString();
        String confirmPassword = binding.confirmPass.getText().toString();

        Call<RegistrationApiResponse> call = apiService.registerUser(firstName, lastName, email, phoneNumber, password, confirmPassword);

        call.enqueue(new Callback<RegistrationApiResponse>() {
            @Override
            public void onResponse(Call<RegistrationApiResponse> call, Response<RegistrationApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegistrationApiResponse apiResponse = response.body();

                    long status = apiResponse.getStatus();
                    String message = apiResponse.getMessge();

                    if (status == 200 && "your data".equals(message)) {
                        Payload userData = apiResponse.getPayload();
                        String token = apiResponse.getToken();

                        Login fragmentB = new Login();

                        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.conatainer, fragmentB);
                        transaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
                        transaction.commit();
                        Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Registration failed: " + message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrationApiResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Registration failed. Check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}