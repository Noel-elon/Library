package com.example.library.Fragments;

import android.net.http.SslError;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.library.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OpenFileFragment extends Fragment {
    WebView webView;
    String url2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_open_file, container, false);
        webView = view.findViewById(R.id.webView);

        String Url = getArguments().getString("URL");
        try {
            url2 = URLEncoder.encode(Url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String someUrl = "http://drive.google.com/viewerng/viewer?embedded=true&url=";
        Log.d("The Url", Url);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
               // handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        webView.loadUrl(someUrl+url2);


        return view;
    }
}
