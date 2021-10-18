package com.adanlm.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.adanlm.daggerpractice.models.User;
import com.adanlm.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        if(authApi == null){
            Log.d(TAG, "AuthViewModel: auth api is NULL");
        } else {
            Log.d(TAG, "AuthViewModel: auth api is Not NULL");
        }
    }

    public void authenticateWithId(int userId){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi
                .getUser(userId)
                .subscribeOn(Schedulers.io()));

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<User> observerUser(){
        return authUser;
    }
}
