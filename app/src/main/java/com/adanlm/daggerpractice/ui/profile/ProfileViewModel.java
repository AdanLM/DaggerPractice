package com.adanlm.daggerpractice.ui.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adanlm.daggerpractice.SessionManager;
import com.adanlm.daggerpractice.models.User;
import com.adanlm.daggerpractice.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: viewModel is ready");
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}
