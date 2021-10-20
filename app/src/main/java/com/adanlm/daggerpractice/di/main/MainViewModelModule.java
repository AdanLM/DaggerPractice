package com.adanlm.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;

import com.adanlm.daggerpractice.di.VIewModelKey;
import com.adanlm.daggerpractice.ui.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @VIewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);
}
