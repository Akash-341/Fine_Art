package com.ort.fineart.Ui.Fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ort.fineart.Ui.Activity.MainActivity;
import com.ort.fineart.Api_Handel.All_URL;
import com.ort.fineart.R;
import com.ort.fineart.Utils.SharedPreferenceManager;
import com.ort.fineart.databinding.FragmentLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends Fragment {

    FragmentLoginBinding binding;
    private SharedPreferenceManager sharedPreferencesManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        sharedPreferencesManager = new SharedPreferenceManager(requireContext());

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
                    login();
                }
            }
        });
        return binding.getRoot();
    }

    public void login() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, All_URL.login,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            int status = jsonResponse.getInt("status");
//                            String message = jsonResponse.getString("messge"); // Correct the typo in "messge" to "message"

                            if (status == 200) {
                                JSONObject customerPayload = jsonResponse.getJSONObject("customer_payload");

                                // Extract user data from customer_payload
                                String userId = customerPayload.getString("id");
                                String userEmail = customerPayload.getString("Email");
                                String userFirstName = customerPayload.getString("FirstName");
                                String userLastName = customerPayload.getString("LastName");
                                String userPhoneNumber = customerPayload.getString("PhoneNumber");

                                // Extract user token
                                String userToken = jsonResponse.getString("customer_token");
                                sharedPreferencesManager.saveUserDetails(userId, userEmail, userFirstName, userLastName, userToken,userPhoneNumber);

                                // Example: Start MainActivity
                                Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);


                                // Show a toast with the success message
                                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                            } else if (status == 400) {
                                // Show a toast with the incorrect password message
                                Toast.makeText(getActivity(), "Incorrect password", Toast.LENGTH_SHORT).show();
                            } else if (status == 401) {
                                // Show a toast with the incorrect email message
                                Toast.makeText(getActivity(), "Incorrect email", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", binding.usernameInput.getText().toString().trim());
                params.put("password", binding.pass.getText().toString().trim());
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);
    }
}