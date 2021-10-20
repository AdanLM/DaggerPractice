package com.adanlm.daggerpractice.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.adanlm.daggerpractice.R;
import com.adanlm.daggerpractice.models.User;
import com.adanlm.daggerpractice.ui.main.MainActivity;
import com.adanlm.daggerpractice.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private AuthViewModel authViewModel;

    private static final String TAG = "AuthActivity";

    private EditText edtUserId;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        edtUserId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        authViewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);
        setLogo();

        subscriveObservers();
    }

    private void subscriveObservers() {
        authViewModel.observerAuthState().observe(this, userAuthResource -> {
            if(userAuthResource != null){
                switch (userAuthResource.status){
                    case LOADING:
                        showProgressBar(true);
                        break;
                    case AUTHENTICATED:
                        showProgressBar(false);
                        Log.d(TAG, "subscriveObservers: " + userAuthResource.data.getEmail());
                        onLoginSuccess();
                        break;
                    case ERROR:
                        showProgressBar(false);
                        Toast.makeText(AuthActivity.this, userAuthResource.message + "\n Did you enter a number between 1 and 10?", Toast.LENGTH_SHORT).show();
                        break;
                    case NOT_AUTHENTICATED:
                        showProgressBar(false);
                        break;
                }
            }
        });
    }

    private void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        } else{
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        String userId = edtUserId.getText().toString();
        if (TextUtils.isEmpty(userId)) {
            return;
        } else {
            authViewModel.authenticateWithId(Integer.parseInt(userId));
        }
    }

    private void onLoginSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}