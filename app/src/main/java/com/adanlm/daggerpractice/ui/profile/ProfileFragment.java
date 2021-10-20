package com.adanlm.daggerpractice.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.adanlm.daggerpractice.R;
import com.adanlm.daggerpractice.models.User;
import com.adanlm.daggerpractice.ui.auth.AuthActivity;
import com.adanlm.daggerpractice.ui.auth.AuthResource;
import com.adanlm.daggerpractice.ui.auth.AuthViewModel;
import com.adanlm.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";

    private ProfileViewModel viewModel;
    private TextView txtEmail, txtUserName, txtWebSite;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), TAG, Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ProfileFragment was created...");
        txtEmail = view.findViewById(R.id.email);
        txtUserName = view.findViewById(R.id.username);
        txtWebSite = view.findViewById(R.id.website);

        viewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);
        subscriveObservers();
    }

    private void subscriveObservers() {
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){
                        case AUTHENTICATED:
                            setUserDetails(userAuthResource.data);
                            break;
                        case ERROR:
                            setErrorDetails(userAuthResource.message);
                            break;
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        txtUserName.setText("Error");
        txtEmail.setText(message);
        txtWebSite.setText("Error");
    }

    private void setUserDetails(User data) {
        txtUserName.setText(data.getUsername());
        txtEmail.setText(data.getEmail());
        txtWebSite.setText(data.getWebsite());
    }
}
