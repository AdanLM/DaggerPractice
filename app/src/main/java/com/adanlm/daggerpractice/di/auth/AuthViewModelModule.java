package com.adanlm.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import com.adanlm.daggerpractice.di.VIewModelKey;
import com.adanlm.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @VIewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);
}
