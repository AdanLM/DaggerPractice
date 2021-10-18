package com.adanlm.daggerpractice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.adanlm.daggerpractice.R;
import com.adanlm.daggerpractice.models.User;
import com.adanlm.daggerpractice.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private AuthViewModel authViewModel;

    private static final String TAG = "AuthActivity";

    private EditText edtUserId;

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

        findViewById(R.id.login_button).setOnClickListener(this);

        authViewModel = new ViewModelProvider(getViewModelStore(), providerFactory).get(AuthViewModel.class);
        setLogo();

        subscriveObservers();
    }

    private void subscriveObservers() {
        authViewModel.observerUser().observe(this, user -> {
            if (user != null) {
                Log.d(TAG, "onChanged: " + user.getEmail());
            }
        });
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
}