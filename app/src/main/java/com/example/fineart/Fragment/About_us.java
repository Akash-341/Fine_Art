package com.example.fineart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.fineart.R;
import com.example.fineart.databinding.FragmentAboutUsBinding;


public class About_us extends Fragment {

   FragmentAboutUsBinding binding;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAboutUsBinding.inflate(inflater, container, false);

   loadWebPage();
        return binding.getRoot();
    }
    private void loadWebPage() {
        // Check if WebView has a valid URL
        if (binding.webview != null) {
            // Load the URL
            binding.webview.loadUrl("http://www.thefinearthub.com/contact_us/");

            // Enable JavaScript
            WebSettings webSettings = binding.webview.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // Set a WebViewClient to handle redirects and errors within the WebView
            binding.webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                    // Handle error loading the webpage
                    Toast.makeText(getContext(), "Error loading the webpage", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle the case when WebView is null
            Toast.makeText(getContext(), "WebView is not available", Toast.LENGTH_SHORT).show();
        }
    }
}