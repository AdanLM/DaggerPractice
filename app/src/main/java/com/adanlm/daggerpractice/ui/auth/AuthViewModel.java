package com.adanlm.daggerpractice.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.adanlm.daggerpractice.SessionManager;
import com.adanlm.daggerpractice.models.User;
import com.adanlm.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;
    private SessionManager sesionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sesionManager) {
        this.authApi = authApi;
        this.sesionManager = sesionManager;
    }

    public void authenticateWithId(int userId) {
        sesionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId)
                //Aqui se obtiene cualquier error posible que pueda ocurrir al consultar la API
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(@NonNull Throwable throwable) throws Exception {
                        User errorUser = new User();
                        errorUser.setId(-1);
                        return errorUser;
                    }
                })
                //Buscamos si existe algun error para usar el wrapper
                .map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(@NonNull User user) throws Exception {
                        if (user.getId() == -1) {
                            return AuthResource.error("Could not authenticate", null);
                        }
                        return AuthResource.authenticated(user);
                    }
                })
                .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observerAuthState() {
        return sesionManager.getAuthUser();
    }
}
